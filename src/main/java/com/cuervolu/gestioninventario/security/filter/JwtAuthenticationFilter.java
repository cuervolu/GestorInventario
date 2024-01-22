package com.cuervolu.gestioninventario.security.filter;

import static com.cuervolu.gestioninventario.security.TokenJwtConfig.AUTHORIZATION_HEADER;
import static com.cuervolu.gestioninventario.security.TokenJwtConfig.TOKEN_PREFIX;

import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.services.IJwtService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final IJwtService jwtService;

  public JwtAuthenticationFilter(
      AuthenticationManager authenticationManager, IJwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    setRequiresAuthenticationRequestMatcher(
        new AntPathRequestMatcher("/api/v1/auth/login", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    UserEntity user;
    String username;
    String password;

    try {
      user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
      username = user.getUsername();
      password = user.getPassword();
    } catch (StreamReadException | DatabindException e) {
      throw new BadCredentialsException("Error en el formato del JSON");
    } catch (IOException e) {
      throw new BadCredentialsException("Error al leer el JSON");
    }

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(username, password);

    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException {

    User user = (User) authResult.getPrincipal();
    String username = user.getUsername();
    Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
    log.debug("roles: {}", roles);
    Claims claims =
        Jwts.claims()
            .add("authorities", new ObjectMapper().writeValueAsString(roles))
            .add("username", username)
            .build();

    String token = jwtService.generateToken(claims, user);

    response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);

    Map<String, String> body = new HashMap<>();
    body.put("token", token);
    body.put("username", username);
    body.put("message", String.format("Hola %s has iniciado sesion con exito!", username));

    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.OK.value());
  }

  @Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
      throws IOException {
    Map<String, String> body = new HashMap<>();
    body.put("message", "Error en la autenticacion username o password incorrectos!");
    body.put("error", failed.getMessage());

    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
  }
}
