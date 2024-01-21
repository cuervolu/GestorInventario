package com.cuervolu.gestioninventario.repositories;

import com.cuervolu.gestioninventario.entities.Role;
import com.cuervolu.gestioninventario.entities.UserRole;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
  Optional<Role> findByName(UserRole name);
}
