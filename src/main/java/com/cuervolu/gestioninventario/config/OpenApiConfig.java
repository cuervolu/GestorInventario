package com.cuervolu.gestioninventario.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info =
        @Info(
            contact = @Contact(name = "Ángel Cuervo", email = "cuervolu@protonmail.com"),
            title = "Gestión de inventario",
            version = "1.0",
            description = "API para la gestión de inventario de la bodega de un hospital",
            license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")),
    security = {
      @SecurityRequirement(
          name = "bearerAuth",
          scopes = {"global"})
    },
    servers = {@Server(url = "http://localhost:8080", description = "Servidor local")})
@SecurityScheme(
    name = "bearerAuth",
    description = "Autenticación JWT",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {}
