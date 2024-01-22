package com.cuervolu.gestioninventario.security.filter;

import static com.cuervolu.gestioninventario.security.TokenJwtConfig.AUTHORIZATION_HEADER;
import static com.cuervolu.gestioninventario.security.TokenJwtConfig.TOKEN_PREFIX;

import com.cuervolu.gestioninventario.services.IJwtService;
import com.cuervolu.gestioninventario.services.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Filtro para validar y procesar tokens JWT en las solicitudes. Se usa en conjunto con {@link
 * JwtAuthenticationFilter}
 *
 * @author Cuervolu
 * @since 1.0.0
 */
public class JwtValidationFilter extends BasicAuthenticationFilter {

  private final UserDetailsServiceImpl userDetailsService;
  private final IJwtService jwtService;

  /**
   * Constructor del filtro de validación JWT.
   *
   * @param authenticationManager Administrador de autenticación.
   * @param userDetailsService Servicio para cargar detalles de usuario.
   * @param jwtService Servicio para operaciones con tokens JWT.
   */
  public JwtValidationFilter(
      AuthenticationManager authenticationManager,
      UserDetailsServiceImpl userDetailsService,
      IJwtService jwtService) {
    super(authenticationManager);
    this.userDetailsService = userDetailsService;
    this.jwtService = jwtService;
  }

  /**
   * Método principal del filtro para validar y procesar tokens JWT.
   *
   * @param request Solicitud HTTP.
   * @param response Respuesta HTTP.
   * @param chain Cadena de filtros.
   * @throws IOException Excepción de E/S.
   * @throws ServletException Excepción de servlet.
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = request.getHeader(AUTHORIZATION_HEADER);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response);
    } else {
      String token = header.replace(TOKEN_PREFIX, "");
      try {
        processToken(token);
        chain.doFilter(request, response);
      } catch (JwtException e) {
        handleJwtException(response, e);
      }
    }
  }

  /**
   * Procesa un token JWT, auténtica al usuario y establece la autenticación en el contexto de
   * seguridad.
   *
   * @param token Token JWT a procesar.
   */
  private void processToken(String token) {
    String username = jwtService.extractUsername(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }

  /**
   * Maneja una excepción JwtException generando una respuesta de error JSON.
   *
   * @param response Respuesta HTTP.
   * @param exception Excepción JwtException.
   * @throws IOException Excepción de E/S.
   */
  private void handleJwtException(HttpServletResponse response, JwtException exception)
      throws IOException {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("error", "Token JWT inválido");
    errorBody.put("message", exception.getMessage());
    errorBody.put("status", HttpStatus.UNAUTHORIZED.value());

    response.getWriter().write(new ObjectMapper().writeValueAsString(errorBody));
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
  }
}
