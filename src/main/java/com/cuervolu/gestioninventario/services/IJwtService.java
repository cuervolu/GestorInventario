package com.cuervolu.gestioninventario.services;

import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interfaz que define operaciones relacionadas con la generación y validación de tokens JWT.
 * 
 * @author Cuervolu
 * @since 1.0.0
 * @version 1.0.0
 */
public interface IJwtService {

  /**
   * Extrae el nombre de usuario de un token JWT.
   *
   * @param token Token JWT del cual se extraerá el nombre de usuario.
   * @return Nombre de usuario extraído del token.
   */
  String extractUsername(String token);

  /**
   * Extrae una reclamación específica de un token JWT.
   *
   * @param <T>            Tipo de la reclamación extraída.
   * @param token          Token JWT del cual se extraerá la reclamación.
   * @param claimsResolver Función que resuelve la reclamación del token.
   * @return Reclamación extraída del token.
   */
  <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  /**
   * Genera un token JWT basado en la información del UserDetails.
   *
   * @param userDetails Detalles del usuario para los cuales se generará el token.
   * @return Token JWT generado.
   */
  String generateToken(UserDetails userDetails);

  /**
   * Genera un token JWT con reclamaciones adicionales basado en la información del UserDetails.
   *
   * @param extraClaims   Reclamaciones adicionales que se agregarán al token.
   * @param userDetails   Detalles del usuario para los cuales se generará el token.
   * @return Token JWT generado con reclamaciones adicionales.
   */
  String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

  /**
   * Genera un token de actualización (refresh token) basado en la información del UserDetails.
   *
   * @param userDetails Detalles del usuario para los cuales se generará el token de actualización.
   * @return Token de actualización (refresh token) generado.
   */
  String generateRefreshToken(UserDetails userDetails);

  /**
   * Verifica si un token JWT es válido para un UserDetails específico.
   *
   * @param token       Token JWT que se verificará.
   * @param userDetails Detalles del usuario para los cuales se verificará el token.
   * @return true si el token es válido, false de lo contrario.
   */
  boolean isTokenValid(String token, UserDetails userDetails);
}
