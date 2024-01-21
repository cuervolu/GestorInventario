package com.cuervolu.gestioninventario.services.impl;

import com.cuervolu.gestioninventario.entities.Category;
import com.cuervolu.gestioninventario.repositories.CategoryRepository;
import com.cuervolu.gestioninventario.services.ICategoryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Category> findAll() {
    return (List<Category>) categoryRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Category> findById(Long id) {
    return categoryRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Category> findByName(String name) {
    return categoryRepository.findByName(name);
  }

  @Override
  @Transactional
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  @Transactional
  public Category update(Long id, Category category) {
    return categoryRepository.save(category);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }
}
