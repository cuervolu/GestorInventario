package com.cuervolu.gestioninventario.repositories;

import com.cuervolu.gestioninventario.entities.Category;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
  
  Optional<Category> findByName(String name);
}
