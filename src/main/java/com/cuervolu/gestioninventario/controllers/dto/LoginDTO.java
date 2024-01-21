package com.cuervolu.gestioninventario.controllers.dto;

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
public class LoginDTO {
  @NotBlank
  @Size(min = 3, max = 50)
  private String username;

  @NotBlank String password;
}
