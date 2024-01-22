package com.cuervolu.gestioninventario.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para la entidad Category.
 *
 * <p>Este DTO se utiliza para la creación y actualización de categorías en el sistema de
 * inventario. Contiene información sobre el nombre de la categoría y una lista de productos
 * asociados.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "CategoryDTO",
    description =
        "Data Transfer Object (DTO) para la entidad Category. Contiene información sobre el nombre de la categoría y una lista de productos asociados.")
public class CategoryDTO {

  /**
   * Nombre de la categoría.
   *
   * <p>El nombre debe tener entre 3 y 150 caracteres.
   *
   * @since 1.0
   */
  @NotNull
  @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
  private String name;

  /**
   * Lista de productos asociados a la categoría.
   *
   * <p>Se valida que la lista de productos sea válida utilizando la anotación @Valid.
   *
   * @since 1.0
   */
  @Valid private List<ProductDTO> products;
}
