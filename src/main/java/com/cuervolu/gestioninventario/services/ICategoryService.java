package com.cuervolu.gestioninventario.services;

import com.cuervolu.gestioninventario.entities.Category;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el servicio de gestión de categorías en el inventario.
 *
 * <p>Define las operaciones básicas que pueden realizarse en relación con las categorías en el
 * sistema de inventario.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
public interface ICategoryService {

  /**
   * Obtiene todas las categorías almacenadas en la base de datos.
   *
   * @return Lista de categorías encontradas.
   */
  List<Category> findAll();

  /**
   * Obtiene una categoría por su ID.
   *
   * @param id ID de la categoría a buscar.
   * @return Optional que contiene la categoría encontrada o está vacío si no se encuentra.
   */
  Optional<Category> findById(Long id);

  /**
   * Obtiene una categoría por su nombre.
   *
   * @param name Nombre de la categoría a buscar.
   * @return Optional que contiene la categoría encontrada o está vacío si no se encuentra.
   */
  Optional<Category> findByName(String name);

  /**
   * Guarda una nueva categoría en la base de datos.
   *
   * @param category Categoría a guardar.
   * @return Categoría guardada.
   */
  Category save(Category category);

  /**
   * Actualiza una categoría existente en la base de datos.
   *
   * @param id ID de la categoría a actualizar.
   * @param category Nueva información de la categoría.
   * @return Categoría actualizada.
   */
  Category update(Long id, Category category);

  /**
   * Elimina una categoría de la base de datos.
   *
   * @param id ID de la categoría a eliminar.
   */
  void delete(Long id);
}
