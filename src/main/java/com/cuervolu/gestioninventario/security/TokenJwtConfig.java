package com.cuervolu.gestioninventario.security;

import com.cuervolu.gestioninventario.services.impl.JwtServiceImpl;
import io.jsonwebtoken.Jwts.SIG;
import javax.crypto.SecretKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Configuración de tokens JWT para la aplicación. Contiene constantes relacionadas con la
 * configuración de tokens JWT y seguridad.
 *
 * @author Cuervolu
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenJwtConfig {
  /**
   * @deprecated Esta constante está marcada como obsoleta. En su lugar, se recomienda utilizar un
   *     mecanismo más seguro y actualizado para generar la clave secreta. Ahora se utiliza {@link
   *     JwtServiceImpl} y sus métodos.
   */
  @Deprecated(forRemoval = true, since = "1.1")
  public static final SecretKey JWT_SECRET_KEY = SIG.HS256.key().build();

  /**
   * Prefijo utilizado en el encabezado 'Authorization' para indicar que el valor es un token JWT.
   */
  public static final String TOKEN_PREFIX = "Bearer ";

  /** Nombre del encabezado utilizado para enviar el token JWT en las solicitudes HTTP. */
  public static final String AUTHORIZATION_HEADER = "Authorization";
}
