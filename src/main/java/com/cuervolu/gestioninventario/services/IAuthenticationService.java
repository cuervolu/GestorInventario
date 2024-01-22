package com.cuervolu.gestioninventario.services;

import com.cuervolu.gestioninventario.controllers.dto.LoginDTO;
import com.cuervolu.gestioninventario.controllers.dto.RegisterDTO;
import com.cuervolu.gestioninventario.entities.payload.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interfaz que define operaciones relacionadas con la autenticación y la generación de tokens.
 */
public interface IAuthenticationService {

  /**
   * Registra un nuevo usuario en el sistema.
   *
   * @param request Datos del usuario a registrar.
   * @return Respuesta de autenticación que incluye el token JWT.
   */
  AuthenticationResponse register(RegisterDTO request);

  /**
   * Autentica a un usuario utilizando credenciales proporcionadas.
   *
   * @param request Datos de inicio de sesión del usuario.
   * @return Respuesta de autenticación que incluye el token JWT.
   */
  AuthenticationResponse authenticate(LoginDTO request);

  /**
   * Renueva el token JWT utilizando un token de actualización.
   *
   * @param request  Solicitud HTTP que contiene el token de actualización.
   * @param response Respuesta HTTP donde se enviará el nuevo token JWT.
   * @throws IOException Si ocurre un error al manejar la respuesta HTTP.
   */
  void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
