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

/**
 * Data Transfer Object (DTO) para la entidad de Producto.
 *
 * <p>Este DTO se utiliza para la creación y actualización de productos en el sistema de inventario.
 * Contiene información sobre el nombre, descripción, precio, stock, esterilidad y la categoría a
 * la que pertenece.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

  /**
   * Nombre del producto.
   *
   * <p>El nombre no puede estar en blanco y debe tener entre 3 y 150 caracteres.
   *
   * @since 1.0
   */
  @NotBlank(message = "El nombre no puede ser nulo")
  @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
  private String name;

  /**
   * Descripción del producto.
   *
   * <p>La descripción no puede estar en blanco y debe tener entre 3 y 250 caracteres.
   *
   * @since 1.0
   */
  @NotBlank(message = "La descripción no puede ser nula")
  @Size(min = 3, max = 250, message = "La descripción debe tener entre 3 y 250 caracteres")
  private String description;

  /**
   * Precio del producto.
   *
   * <p>El precio no puede ser negativo.
   *
   * @since 1.0
   */
  @Min(value = 0, message = "El precio no puede ser negativo")
  private double price;

  /**
   * Stock disponible del producto.
   *
   * <p>El stock no puede ser negativo.
   *
   * @since 1.0
   */
  @Min(value = 0, message = "El stock no puede ser negativo")
  private Integer stock;

  /**
   * Indica si el producto es estéril o no.
   *
   * <p>El campo sterile no puede ser nulo.
   *
   * @since 1.0
   */
  @NotNull(message = "El campo sterile no puede ser nulo")
  private Boolean sterile;

  /**
   * Categoría a la que pertenece el producto.
   *
   * <p>Se valida que la categoría sea válida utilizando la anotación @Valid.
   *
   * @since 1.0
   */
  @Valid private CategoryDTO category;
}
