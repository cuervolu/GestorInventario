package com.cuervolu.gestioninventario.services;

import com.cuervolu.gestioninventario.entities.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
  List<Category> findAll();

  Optional<Category> findById(Long id);
  
  Optional<Category> findByName(String name);

  Category save(Category category);

  Category update(Long id, Category category);

  void delete(Long id);
}
