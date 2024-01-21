package com.cuervolu.gestioninventario.controllers;

import com.cuervolu.gestioninventario.controllers.dto.ProductDTO;
import com.cuervolu.gestioninventario.entities.Category;
import com.cuervolu.gestioninventario.entities.Product;
import com.cuervolu.gestioninventario.entities.payload.ResponseMessage;
import com.cuervolu.gestioninventario.services.ICategoryService;
import com.cuervolu.gestioninventario.services.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "El API de productos para la gestión de inventario")
public class ProductController {

  private final IProductService productService;
  private final ICategoryService categoryService;

  @Operation(
      summary = "Encuentra todos los productos",
      description = "Encuentra todos los productos en la base de datos y los retorna en una lista",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos encontrados",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Product.class))
            })
      })
  @GetMapping
  public List<Product> findAll() {
    return productService.findAll();
  }

  @Operation(
      summary = "Encuentra un producto por su id",
      description = "Encuentra un producto por su id en la base de datos y lo retorna",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto encontrado",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Product.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            })
      })
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      return ResponseEntity.ok(product.get());
    }
    return ResponseEntity.notFound().build();
  }

  @Operation(
      summary = "Crea un producto",
      description = "Crea un producto en la base de datos y lo retorna",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "El producto a crear",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProductDTO.class))),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Producto creado",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Product.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "La categoría no existe",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Los parámetros del producto son inválidos",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            })
      })
  @PostMapping
  public ResponseEntity<?> save(@RequestBody @Valid ProductDTO productDTO)
      throws URISyntaxException {
    Optional<Category> category = findCategoryByName(productDTO.getCategory().getName());

    if (category.isEmpty()) {
      return new ResponseEntity<>(
          ResponseMessage.builder()
              .message("El nombre de la categoría no existe")
              .object(null)
              .build(),
          HttpStatus.NOT_FOUND);
    }

    Product product =
        Product.builder()
            .name(productDTO.getName())
            .description(productDTO.getDescription())
            .category(category.get())
            .price(productDTO.getPrice())
            .stock(productDTO.getStock())
            .build();
    return ResponseEntity.created(new URI("/api/v1/products/" + product.getId()))
        .body(productService.save(product));
  }

  @Operation(
      summary = "Actualiza un producto",
      description = "Actualiza un producto en la base de datos y lo retorna",
      parameters = {
        @Parameter(name = "id", description = "El id del producto", required = true),
      },
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "El producto a actualizar",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ProductDTO.class))),
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto actualizado",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Product.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Producto o categoría no encontrado",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Los parámetros del producto son inválidos",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            })
      })
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO)
      throws URISyntaxException {
    Optional<Category> category = findCategoryByName(productDTO.getCategory().getName());

    if (category.isEmpty()) {
      return new ResponseEntity<>(
          ResponseMessage.builder()
              .message("El nombre de la categoría no existe")
              .object(null)
              .build(),
          HttpStatus.NOT_FOUND);
    }

    Optional<Product> productOptional = productService.findById(id);

    if (productOptional.isPresent()) {
      Product product = productOptional.get();
      product.setName(productDTO.getName());
      product.setDescription(productDTO.getDescription());
      product.setCategory(category.get());
      product.setPrice(productDTO.getPrice());
      product.setStock(productDTO.getStock());
      return ResponseEntity.ok().body(productService.save(product));
    }
    return ResponseEntity.notFound().build();
  }

  @Operation(
      summary = "Elimina un producto",
      parameters = @Parameter(name = "id", description = "El id del producto", required = true),
      description = "Elimina un producto en la base de datos",
      responses = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado"),
        @ApiResponse(
            responseCode = "400",
            description = "El id del producto es inválido",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ResponseMessage.class))
            }),
      })
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(
              ResponseMessage.builder()
                  .message("El id no puede ser nulo o menor o igual a cero")
                  .object(null)
                  .build());
    }

    productService.delete(id);
    return ResponseEntity.noContent().build();
  }

  private Optional<Category> findCategoryByName(String categoryName) {
    return categoryService.findByName(categoryName);
  }
}
