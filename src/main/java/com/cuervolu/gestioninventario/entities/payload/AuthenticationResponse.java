package com.cuervolu.gestioninventario.entities.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la respuesta de autenticación del sistema.
 *
 * <p>Contiene información sobre el token de acceso y el token de actualización generados durante
 * el proceso de autenticación.</p>
 *
 * @author Cuervolu
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;
}
