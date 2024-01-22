package com.cuervolu.gestioninventario.services.impl;

import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.repositories.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link UserDetailsService} que carga detalles de usuario desde la base de
 * datos. Esta implementación se utiliza en la autenticación de Spring Security para cargar detalles
 * de usuario durante la autenticación.
 *
 * <p>Los detalles de usuario incluyen el nombre de usuario, la contraseña, las autoridades y
 * propiedades adicionales como la disponibilidad de la cuenta y las credenciales expiradas.
 *
 * <p>Esta implementación utiliza un repositorio de usuarios para acceder a la base de datos y
 * recuperar los detalles del usuario.
 *
 * @author Cuervolu
 * @since 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository repository;

  /**
   * Carga los detalles del usuario por nombre de usuario.
   *
   * @param username Nombre de usuario para el que se cargarán los detalles.
   * @return Detalles del usuario, incluidas las autoridades y propiedades adicionales.
   * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos.
   */
  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> userEntityOptional = repository.findByUsername(username);

    if (userEntityOptional.isEmpty()) {
      throw new UsernameNotFoundException(
          String.format("User with username %s not found", username));
    }
    UserEntity userEntity = userEntityOptional.get();

    Collection<? extends GrantedAuthority> authorities =
        userEntity.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toSet());

    return User.builder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .authorities(authorities)
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(!userEntity.getEnabled())
        .build();
  }
}
