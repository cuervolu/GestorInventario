package com.cuervolu.gestioninventario.controllers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
  @NotBlank(message = "El nombre no puede ser nulo")
  @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
  private String name;

  @NotBlank(message = "La descripción no puede ser nula")
  @Size(min = 3, max = 250, message = "La descripción debe tener entre 3 y 250 caracteres")
  private String description;

  @Min(value = 0, message = "El precio no puede ser negativo")
  private double price;

  @Min(value = 0, message = "El stock no puede ser negativo")
  private Integer stock;

  @NotNull(message = "El campo sterile no puede ser nulo")
  private Boolean sterile;

  @Valid private CategoryDTO category;
}
