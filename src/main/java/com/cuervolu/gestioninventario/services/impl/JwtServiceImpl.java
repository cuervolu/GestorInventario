package com.cuervolu.gestioninventario.services.impl;

import com.cuervolu.gestioninventario.services.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Implementación de {@link IJwtService} que proporciona funcionalidad relacionada con tokens JWT.
 * Esta implementación utiliza la biblioteca de JSON Web Tokens (JWT).
 * <p>
 * <b>La configuración, como la clave secreta y los tiempos de expiración, se obtiene de las
 * propiedades de la aplicación.</b>
 *
 * @author Cuervolu
 * @since 1.0.0
 */
@Service
public class JwtServiceImpl implements IJwtService {

  /** Clave secreta utilizada para firmar y verificar tokens JWT. */
  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  /** Tiempo de expiración del token JWT en milisegundos. */
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  /** Tiempo de expiración del token de actualización en milisegundos. */
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  @Override
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  @Override
  public String generateRefreshToken(UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  /**
   * Construye un token JWT con los claims proporcionados y los tiempos de expiración especificados.
   *
   * @param extraClaims Claims adicionales a incluir en el token.
   * @param userDetails Detalles del usuario para los que se genera el token.
   * @param expiration Tiempo de expiración del token en milisegundos.
   * @return Token JWT generado.
   */
  private String buildToken(
      Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
    return Jwts.builder()
        .claims(extraClaims)
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey())
        .compact();
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  /**
   * Verifica si un token JWT ha expirado.
   *
   * @param token Token JWT a verificar.
   * @return {@code true} si el token ha expirado, {@code false} en caso contrario.
   */
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
   * Extrae la fecha de expiración de un token JWT.
   *
   * @param token Token JWT del que se extrae la fecha de expiración.
   * @return Fecha de expiración del token.
   */
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  /**
   * Extrae todos los claims de un token JWT verificándolo con la clave de firma.
   *
   * @param token Token JWT del que se extraen los claims.
   * @return Claims extraídos del token.
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
  }

  /**
   * Extrae todos los claims de un token JWT. <b>Esta versión no verifica la firma del token y se
   * utiliza en lugar de la ya deprecada JWT_SECRET_KEY.</b>
   *
   * <p>Nota: La verificación de la firma proporciona una capa adicional de seguridad al asegurar que
   * el token no ha sido alterado por terceros.</p>
   *
   * @return Claims extraídos del token.
   */
  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
