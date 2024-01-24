package com.cuervolu.gestioninventario.services.impl;

import com.cuervolu.gestioninventario.controllers.dto.ChangePasswordDTO;
import com.cuervolu.gestioninventario.entities.Role;
import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.entities.UserRole;
import com.cuervolu.gestioninventario.repositories.RoleRepository;
import com.cuervolu.gestioninventario.repositories.UserRepository;
import com.cuervolu.gestioninventario.services.IUserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    Optional<Role> optionalRole = roleRepository.findByName(UserRole.USER);
    Set<Role> roles = new HashSet<>();
    optionalRole.ifPresent(roles::add);

    if (userEntity.isAdmin()) {
      Optional<Role> optionalRoleAdmin = roleRepository.findByName(UserRole.ADMIN);
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

  @Override
  @Transactional
  public void changePassword(ChangePasswordDTO request, Principal connectedUser) {
    // Asignamos el usuario conectado a la variable user, proveniente del parámetro connectedUser
    var username = ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    Optional<UserEntity> userOptional = userRepository.findByUsername(username.toString());
    if (userOptional.isPresent()) {
      UserEntity user = userOptional.get();
      // Comprobamos que la contraseña actual sea correcta
      if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
        throw new IllegalStateException("La contraseña actual no es correcta");
      }

      // Comprobamos que la nueva contraseña y la confirmación coincidan
      if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
        throw new IllegalStateException("Las contraseñas no coinciden");
      }

      // Cambiamos la contraseña del usuario
      user.setPassword(passwordEncoder.encode(request.getNewPassword()));

      // Guardamos el usuario en la base de datos
      userRepository.save(user);
    } else {
      throw new IllegalStateException("El usuario no existe");
    }
  }
}
