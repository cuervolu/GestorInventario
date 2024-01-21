package com.cuervolu.gestioninventario.controllers;

import com.cuervolu.gestioninventario.controllers.dto.CategoryDTO;
import com.cuervolu.gestioninventario.entities.Category;
import com.cuervolu.gestioninventario.entities.Product;
import com.cuervolu.gestioninventario.entities.payload.ResponseMessage;
import com.cuervolu.gestioninventario.services.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "El API de categorías para la gestión de inventario")
public class CategoryController {
  private final ICategoryService categoryService;

  @Operation(
      summary = "Encuentra todas las categorías",
      description = "Encuentra todas las categorías en la base de datos y los retorna en una lista",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de categorías encontrados",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Category.class))
            })
      })
  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @Operation(
      summary = "Encuentra una categoría por su id",
      description = "Encuentra una categoría en la base de datos y la retorna",
      parameters = {
        @Parameter(
            name = "id",
            description = "El id de la categoría a buscar",
            required = true,
            example = "1")
      },
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Categoría encontrada",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Category.class))
            }),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada",content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ResponseMessage.class))
        }),
      })
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<Category> category = categoryService.findById(id);
    if (category.isPresent()) {
      return ResponseEntity.ok(category.get());
    }
    return ResponseEntity.notFound().build();
  }

  @Operation(
      summary = "Crea una categoría",
      description = "Crea una categoría en la base de datos y la retorna",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "La categoría a crear",
              required = true,
              content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class))
              }),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Categoría creada",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Category.class))
            }),
        @ApiResponse(responseCode = "400", description = "Categoría inválida",content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ResponseMessage.class))
        }),
      })
  @PostMapping
  public ResponseEntity<Category> save(@RequestBody CategoryDTO categoryDTO)
      throws URISyntaxException {
    Category category = Category.builder().name(categoryDTO.getName()).build();
    return ResponseEntity.created(new URI("/api/v1/products/" + category.getId()))
        .body(categoryService.save(category));
  }

  @Operation(
      summary = "Actualiza una categoría",
      description = "Actualiza una categoría en la base de datos y la retorna",
      parameters =
          @Parameter(
              name = "id",
              description = "El id de la categoría a actualizar",
              required = true,
              example = "1"),
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "La categoría a actualizar",
              required = true,
              content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class))
              }),
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Categoría actualizada",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Category.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Categoría no encontrada",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Categoría inválida",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            }),
      })
  @PutMapping("/{id}")
  public ResponseEntity<Category> update(
      @PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
    Optional<Category> categoryOptional = categoryService.findById(id);

    if (categoryOptional.isPresent()) {
      Category category = categoryOptional.get();
      category.setName(categoryDTO.getName());
      return ResponseEntity.ok().body(categoryService.save(category));
    }
    return ResponseEntity.notFound().build();
  }

  @Operation(
      summary = "Elimina una categoría",
      description = "Elimina una categoría en la base de datos",
      parameters =
          @Parameter(
              name = "id",
              description = "El id de la categoría a eliminar",
              required = true,
              example = "1"),
      responses = {
        @ApiResponse(responseCode = "204", description = "Categoría eliminada"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Category> categoryOptional = categoryService.findById(id);

    if (categoryOptional.isPresent()) {
      categoryService.delete(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
