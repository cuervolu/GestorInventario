package com.cuervolu.gestioninventario.security.filter;

import static com.cuervolu.gestioninventario.security.TokenJwtConfig.AUTHORIZATION_HEADER;
import static com.cuervolu.gestioninventario.security.TokenJwtConfig.TOKEN_PREFIX;

import com.cuervolu.gestioninventario.repositories.TokenRepository;
import com.cuervolu.gestioninventario.services.IJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final TokenRepository tokenRepository;
  private final UserDetailsService userDetailsService;
  private final IJwtService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().contains("/api/v1/auth")) {
      filterChain.doFilter(request, response);
      return;
    }
    final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
    final String jwt;
    final String username;
    if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.replace(TOKEN_PREFIX, "");
    username = jwtService.extractUsername(jwt);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      var isTokenValid =
          tokenRepository.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
      if (jwtService.isTokenValid(jwt, userDetails) && Boolean.TRUE.equals(isTokenValid)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
