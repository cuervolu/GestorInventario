package com.cuervolu.gestioninventario.repositories;

import com.cuervolu.gestioninventario.entities.Product;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {}
