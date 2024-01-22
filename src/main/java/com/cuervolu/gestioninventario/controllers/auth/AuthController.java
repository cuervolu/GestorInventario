package com.cuervolu.gestioninventario.controllers.auth;

import com.cuervolu.gestioninventario.controllers.dto.LoginDTO;
import com.cuervolu.gestioninventario.controllers.dto.RegisterDTO;
import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.entities.payload.AuthenticationResponse;
import com.cuervolu.gestioninventario.services.IAuthenticationService;
import com.cuervolu.gestioninventario.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "El Endpoint de autenticación para la gestión de inventario")
public class AuthController {

  private final IAuthenticationService authenticationService;

  @Operation(
      summary = "Registrar un usuario",
      tags = {"Auth"},
      operationId = "register",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario registrado",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Error al registrar el usuario",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class)))
      },
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Credenciales del usuario",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = RegisterDTO.class))))
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDTO userEntity) {
    return ResponseEntity.ok(authenticationService.register(userEntity));
  }

  @Operation(
      summary = "Autenticar un usuario",
      tags = {"Auth"},
      operationId = "authenticate",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario autenticado",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Error al autenticar el usuario",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthenticationResponse.class)))
      },
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Credenciales del usuario",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = LoginDTO.class))))
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginDTO request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

  @Operation(
      summary = "Refrescar el token",
      tags = {"Auth"},
      operationId = "refreshToken")
  @PostMapping("refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    authenticationService.refreshToken(request, response);
  }
}
