package com.cuervolu.gestioninventario.services.impl;

import com.cuervolu.gestioninventario.entities.Role;
import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.entities.UserRole;
import com.cuervolu.gestioninventario.repositories.RoleRepository;
import com.cuervolu.gestioninventario.repositories.UserRepository;
import com.cuervolu.gestioninventario.services.IUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional(readOnly = true)
  public List<UserEntity> findAll() {
    return (List<UserEntity>) userRepository.findAll();
  }

  @Override
  @Transactional
  public UserEntity save(UserEntity userEntity) {
    Optional<Role> optionalRole = roleRepository.findByName(UserRole.ROLE_USER);
    List<Role> roles = new ArrayList<>();
    optionalRole.ifPresent(roles::add);

    if (userEntity.isAdmin()) {
      Optional<Role> optionalRoleAdmin = roleRepository.findByName(UserRole.ROLE_ADMIN);
      optionalRoleAdmin.ifPresent(roles::add);
    }

    userEntity.setRoles(roles);
    String password = passwordEncoder.encode(userEntity.getPassword());
    userEntity.setPassword(password);
    return userRepository.save(userEntity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserEntity> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserEntity> findById(Long id) {
    return userRepository.findById(id);
  }
}
