package com.cuervolu.gestioninventario.controllers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
  
  @NotNull
  @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
  private String name;
  
  @Valid
  private List<ProductDTO> products;
}
