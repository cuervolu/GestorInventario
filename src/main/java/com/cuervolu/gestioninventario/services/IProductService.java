package com.cuervolu.gestioninventario.services;

import com.cuervolu.gestioninventario.entities.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
  List<Product> findAll();

  Optional<Product> findById(Long id);

  Product save(Product product);
  
  Product update(Long id, Product product);

  void delete(Long id);
}
