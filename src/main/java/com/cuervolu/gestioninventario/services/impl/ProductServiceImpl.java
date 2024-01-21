package com.cuervolu.gestioninventario.services.impl;

import com.cuervolu.gestioninventario.entities.Product;
import com.cuervolu.gestioninventario.repositories.ProductRepository;
import com.cuervolu.gestioninventario.services.IProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

  private final ProductRepository productRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Product> findAll() {
    return (List<Product>) productRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  @Override
  @Transactional
  public Product save(Product product) {
    return this.productRepository.save(product);
  }

  @Override
  public Product update(Long id, Product product) {
    return this.productRepository.save(product);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    productRepository.deleteById(id);
  }
}
