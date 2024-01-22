package com.cuervolu.gestioninventario.controllers;

import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.entities.UserRole;
import com.cuervolu.gestioninventario.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para la gestión de usuarios en el inventario.
 *
 * <p>Esta clase es la encargada de gestionar las peticiones que tienen que ver con los usuarios del
 * inventario.
 *
 * <p>Las operaciones que se pueden realizar son:
 *
 * <ul>
 *   <li>Obtener todos los usuarios
 *   <li>Obtener un usuario por su id
 * </ul>
 *
 * Esta clase está protegida por el rol ADMIN. {@link UserRole}
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "El EndPoint de Users para la gestión de inventario")
public class UserController {
  private final IUserService userService;

  /**
   * Obtiene todos los usuarios habilitados. Estos usuarios son los que tienen el campo enabled a
   * {@code true}
   *
   * <p>De no existir usuarios, se devuelve una lista vacía.
   *
   * @return Lista de usuarios.
   */
  @Operation(
      summary = "Obtener todos los usuarios",
      description = "Obtener todos los usuarios habilitados",
      tags = {"Users"},
      operationId = "findAll",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de usuarios",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos para acceder a este recurso",
            content = @Content(mediaType = "application/json"))
      })
  @GetMapping("/")
  public List<UserEntity> findAll() {
    return userService.findAll();
  }

  /**
   * Obtiene un usuario por su ID.
   *
   * @param id ID del usuario obtenido de la URL (PathVariable).
   * @return ResponseEntity con el usuario encontrado o un mensaje de error si no se encuentra.
   */
  @Operation(
      summary = "Obtener un usuario por su id",
      tags = {"Users"},
      operationId = "findById",
      parameters =
          @Parameter(
              name = "id",
              description = "Id del usuario",
              required = true,
              example = "1",
              in = ParameterIn.PATH,
              schema = @Schema(type = "long")),
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos para acceder a este recurso",
            content = @Content(mediaType = "application/json"))
      })
  @GetMapping("{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<UserEntity> userEntity = userService.findById(id);
    if (userEntity.isPresent()) {
      return ResponseEntity.ok().body(userEntity);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
