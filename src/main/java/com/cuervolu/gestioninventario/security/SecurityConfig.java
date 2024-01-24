package com.cuervolu.gestioninventario.security;

import com.cuervolu.gestioninventario.security.filter.JwtAuthenticationFilter;
import com.cuervolu.gestioninventario.services.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad para la aplicación. Define la configuración para la autenticación y
 * autorización, así como los filtros personalizados para el manejo de tokens JWT.
 *
 * <p>Se utiliza para configurar las reglas de seguridad de las solicitudes HTTP y definir cómo se
 * manejarán los tokens JWT en la aplicación.
 *
 * @author Cuervolu
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
  /**
   * Lista de URL que están exentas de la autenticación. Se permite el acceso sin autenticación a
   * estas URL, que incluyen rutas de autenticación, recursos de Swagger y páginas de inicio de
   * sesión.
   */
  private static final String[] WHITE_LIST_URL = {
    "/api/v1/auth/**",
    "/api/v1/api-docs",
    "/v2/api-docs",
    "/v3/api-docs",
    "/v3/api-docs/**",
    "/swagger-resources",
    "/swagger-resources/**",
    "/configuration/ui",
    "/configuration/security",
    "/swagger-ui/**",
    "/webjars/**",
    "/swagger-ui.html",
    "/login",
  };

  private final AuthenticationConfiguration authenticationConfiguration;
  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  /**
   * Configuración del administrador de autenticación para ser utilizado en la aplicación.
   *
   * @return Un {@link AuthenticationManager} configurado.
   * @throws Exception Sí hay algún error al configurar el administrador de autenticación.
   */
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Configuración de la cadena de filtros de seguridad para gestionar la autenticación y
   * autorización de las solicitudes HTTP.
   *
   * @param http Configuración de seguridad HTTP.
   * @return Una cadena de filtros de seguridad configurada.
   * @throws Exception Sí hay algún error al configurar la cadena de filtros de seguridad.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth -> {
              auth.requestMatchers(WHITE_LIST_URL).permitAll();
              auth.anyRequest().authenticated();
            })
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }
}
