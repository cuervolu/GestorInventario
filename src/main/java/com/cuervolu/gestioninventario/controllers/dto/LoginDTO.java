package com.cuervolu.gestioninventario.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para las credenciales de inicio de sesión.
 *
 * <p>Este DTO se utiliza para representar la información de inicio de sesión, incluyendo el nombre
 * de usuario y la contraseña.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "LoginDTO",
    description = "Login data transfer object",
    example = "{\"username\":\"admin\",\"password\":\"admin\"}")
public class LoginDTO {

  /**
   * Nombre de usuario para el inicio de sesión.
   *
   * <p>El nombre de usuario no puede estar en blanco y debe tener entre 3 y 50 caracteres.
   *
   * @since 1.0
   */
  @NotBlank
  @Size(min = 3, max = 50)
  private String username;

  /**
   * Contraseña para el inicio de sesión.
   *
   * <p>La contraseña no puede estar en blanco.
   *
   * @since 1.0
   */
  @NotBlank
  private String password;
}
