package com.cuervolu.gestioninventario.repositories;

import com.cuervolu.gestioninventario.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
  Optional<UserEntity> findByUsername(String username);
  
}
