package com.cuervolu.gestioninventario.config;

import com.cuervolu.gestioninventario.auditing.ApplicationAuditAware;
import com.cuervolu.gestioninventario.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Configuración general de la aplicación. */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
  private final UserRepository repository;

  /**
   * Configura el proveedor de auditoría para la auditoría de entidades.
   *
   * @return Una instancia de {@link AuditorAware} configurada con {@link ApplicationAuditAware}.
   */
  @Bean
  public AuditorAware<Long> auditorAware() {
    return new ApplicationAuditAware();
  }

  /**
   * Configura el proveedor de autenticación utilizado para la autenticación basada en DAO.
   *
   * @return Un {@link AuthenticationProvider} configurado con {@link DaoAuthenticationProvider}.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**
   * Configura el servicio de detalles de usuario para la autenticación.
   *
   * @return Un {@link UserDetailsService} que busca el usuario en el repositorio.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username ->
        repository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
  }

  /**
   * Configuración del codificador de contraseñas utilizado para cifrar y verificar contraseñas.
   *
   * @return Un {@link PasswordEncoder} configurado.
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
