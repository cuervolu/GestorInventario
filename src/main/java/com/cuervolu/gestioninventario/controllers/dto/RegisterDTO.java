package com.cuervolu.gestioninventario.controllers.dto;

import com.cuervolu.gestioninventario.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
  
  private String firstname;
  private String lastname;
  @NotBlank
  @Size(min = 3, max = 50)
  private String username;

  @Email
  @NotBlank
  private String email;
  
  @NotBlank
  private String password;
  private Role role;
}
