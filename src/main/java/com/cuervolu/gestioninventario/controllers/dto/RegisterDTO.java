package com.cuervolu.gestioninventario.controllers.dto;

import com.cuervolu.gestioninventario.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para el registro de usuarios.
 *
 * <p>Este DTO se utiliza para recopilar la información necesaria al registrar un nuevo usuario en el
 * sistema de inventario. Contiene detalles como el nombre, apellido, nombre de usuario, correo
 * electrónico, contraseña y el rol del usuario.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

  /**
   * Primer nombre del usuario.
   *
   * @since 1.0
   */
  private String firstname;

  /**
   * Apellido del usuario.
   *
   * @since 1.0
   */
  private String lastname;

  /**
   * Nombre de usuario.
   *
   * <p>El nombre de usuario no puede estar en blanco y debe tener entre 3 y 50 caracteres.
   *
   * @since 1.0
   */
  @NotBlank
  @Size(min = 3, max = 50)
  private String username;

  /**
   * Correo electrónico del usuario.
   *
   * <p>El correo electrónico debe ser válido y no puede estar en blanco.
   *
   * @since 1.0
   */
  @Email
  @NotBlank
  private String email;

  /**
   * Contraseña del usuario.
   *
   * <p>La contraseña no puede estar en blanco.
   *
   * @since 1.0
   */
  @NotBlank
  private String password;

  /**
   * Rol asignado al usuario.
   *
   * @since 1.0
   */
  private Role role;
}
