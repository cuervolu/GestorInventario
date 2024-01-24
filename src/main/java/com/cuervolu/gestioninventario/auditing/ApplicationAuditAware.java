package com.cuervolu.gestioninventario.auditing;

import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.repositories.UserRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Implementación de {@link AuditorAware} para proporcionar el auditor actual para la auditoría de
 * entidades.
 */
@RequiredArgsConstructor
public class ApplicationAuditAware implements AuditorAware<Long> {

  /**
   * Obtiene el ID del auditor actual.
   *
   * @return Un {@link Optional} que contiene el ID del auditor actual si está disponible.
   */
  @WithSpan
  @Override
  public Optional<Long> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // Verifica si la autenticación es nula, no está autenticada o es anónima
    if (authentication == null
        || !authentication.isAuthenticated()
        || authentication instanceof AnonymousAuthenticationToken) {
      return Optional.empty();
    }
    // Obtiene el principal del usuario desde los detalles de autenticación
    UserEntity userPrincipal = (UserEntity) authentication.getPrincipal();

    // Devuelve el ID del usuario como auditor actual
    return Optional.ofNullable(userPrincipal.getId());
  }
}
