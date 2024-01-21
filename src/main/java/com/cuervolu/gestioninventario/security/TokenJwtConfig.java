package com.cuervolu.gestioninventario.security;

import io.jsonwebtoken.Jwts.SIG;
import javax.crypto.SecretKey;

public class TokenJwtConfig {
  public static final SecretKey SECRET_KEY = SIG.HS256.key().build();
  public static final String PREFIX_TOKEN = "Bearer ";
  public static final String HEADER_AUTHORIZATION = "Authorization";
}
