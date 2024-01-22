package com.cuervolu.gestioninventario.services;

import com.cuervolu.gestioninventario.entities.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el servicio de gestión de productos en el inventario.
 *
 * <p>Define las operaciones básicas que pueden realizarse en relación con los productos en el
 * sistema de inventario.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
public interface IProductService {

  /**
   * Obtiene todos los productos almacenados en la base de datos.
   *
   * @return Lista de productos encontrados.
   */
  List<Product> findAll();

  /**
   * Obtiene un producto por su ID.
   *
   * @param id ID del producto a buscar.
   * @return Optional que contiene el producto encontrado o está vacío si no se encuentra.
   */
  Optional<Product> findById(Long id);

  /**
   * Guarda un nuevo producto en la base de datos.
   *
   * @param product Producto a guardar.
   * @return Producto guardado.
   */
  Product save(Product product);

  /**
   * Actualiza un producto existente en la base de datos.
   *
   * @param id ID del producto a actualizar.
   * @param product Nueva información del producto.
   * @return Producto actualizado.
   */
  Product update(Long id, Product product);

  /**
   * Elimina un producto de la base de datos.
   *
   * @param id ID del producto a eliminar.
   */
  void delete(Long id);
}
