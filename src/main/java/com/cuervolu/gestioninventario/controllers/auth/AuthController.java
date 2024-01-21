package com.cuervolu.gestioninventario.controllers.auth;

import com.cuervolu.gestioninventario.controllers.dto.RegisterDTO;
import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.services.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  private final IUserService userService;
  
  @PostMapping("/register")
  public ResponseEntity<UserEntity> register(@RequestBody RegisterDTO userEntity)
      throws URISyntaxException {
    UserEntity user =
        UserEntity.builder()
            .email(userEntity.getEmail())
            .firstName(userEntity.getFirstname())
            .lastName(userEntity.getLastname())
            .password(userEntity.getPassword())
            .username(userEntity.getUsername())
            .enabled(true)
            .admin(false)
            .build();

    return ResponseEntity.created(new URI("/api/v1/users/" + user.getId()))
        .body(userService.save(user));
  }
  
}
