package com.cuervolu.gestioninventario.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para cambiar la contraseña del usuario.
 *
 * <p>Este DTO se utiliza para cambiar la contraseña del usuario. Contiene información sobre la
 * contraseña actual, la nueva contraseña y la confirmación de la nueva contraseña.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
@Getter
@Setter
@Builder
public class ChangePasswordDTO {
  /**
   * Contraseña actual del usuario.
   *
   * <p>La contraseña actual no puede estar en blanco.
   *
   * @since 1.0
   */
  @NotBlank(message = "El campo currentPassword no puede estar vacío")
  private String currentPassword;

  /**
   * Nueva contraseña del usuario.
   *
   * <p>La nueva contraseña no puede estar en blanco.
   *
   * @since 1.0
   */
  @NotBlank(message = "El campo newPassword no puede estar vacío")
  private String newPassword;

  /**
   * Confirmación de la nueva contraseña del usuario.
   *
   * <p>La confirmación de la nueva contraseña no puede estar en blanco.
   *
   * @since 1.0
   */
  @NotBlank(message = "El campo confirmationPassword no puede estar vacío")
  private String confirmationPassword;
}
