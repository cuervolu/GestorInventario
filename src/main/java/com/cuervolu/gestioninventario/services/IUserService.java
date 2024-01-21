package com.cuervolu.gestioninventario.services;

import com.cuervolu.gestioninventario.entities.UserEntity;
import java.util.List;
import java.util.Optional;

public interface IUserService {
  List<UserEntity> findAll();
  
  UserEntity save(UserEntity userEntity);
  
  Optional<UserEntity> findByUsername(String username);
  
  Optional<UserEntity> findById(Long id);
}
