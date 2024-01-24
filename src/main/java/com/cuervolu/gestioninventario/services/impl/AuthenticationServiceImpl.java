package com.cuervolu.gestioninventario.services.impl;

import static com.cuervolu.gestioninventario.security.TokenJwtConfig.AUTHORIZATION_HEADER;
import static com.cuervolu.gestioninventario.security.TokenJwtConfig.TOKEN_PREFIX;

import com.cuervolu.gestioninventario.controllers.dto.LoginDTO;
import com.cuervolu.gestioninventario.controllers.dto.RegisterDTO;
import com.cuervolu.gestioninventario.entities.Role;
import com.cuervolu.gestioninventario.entities.Token;
import com.cuervolu.gestioninventario.entities.TokenType;
import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.entities.UserRole;
import com.cuervolu.gestioninventario.entities.payload.AuthenticationResponse;
import com.cuervolu.gestioninventario.repositories.TokenRepository;
import com.cuervolu.gestioninventario.services.IAuthenticationService;
import com.cuervolu.gestioninventario.services.IJwtService;
import com.cuervolu.gestioninventario.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

  private final TokenRepository tokenRepository;
  private final UserDetailsService userDetailsService;
  private final IUserService userService;
  private final IJwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  @Transactional
  public AuthenticationResponse register(RegisterDTO request) {
    UserEntity user =
        UserEntity.builder()
            .email(request.getEmail())
            .firstName(request.getFirstname())
            .lastName(request.getLastname())
            .password(request.getPassword())
            .username(request.getUsername())
            .isAccountNonLocked(true)
            .isAccountNonExpired(true)
            .isCredentialsNonExpired(true)
            .enabled(true)
            .build();

    if (UserRole.ADMIN.equals(request.getRole().getName())) {
      user.setAdmin(true);
    }

    var savedUser = userService.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  @Override
  @Transactional
  public AuthenticationResponse authenticate(LoginDTO request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    var user = userDetailsService.loadUserByUsername(request.getUsername());
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens((UserEntity) user);
    saveUserToken((UserEntity) user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(UserEntity user, String jwtToken) {
    var token =
        Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(UserEntity user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty()) return;
    validUserTokens.forEach(
        token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });
    tokenRepository.saveAll(validUserTokens);
  }

  @Override
  @Transactional
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
    final String refreshToken;
    final String username;
    if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
      return;
    }
    refreshToken = authHeader.substring(7);
    username = jwtService.extractUsername(refreshToken);
    if (username != null) {
      var user = userDetailsService.loadUserByUsername(username);
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens((UserEntity) user);
        saveUserToken((UserEntity) user, accessToken);
        var authResponse =
            AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
