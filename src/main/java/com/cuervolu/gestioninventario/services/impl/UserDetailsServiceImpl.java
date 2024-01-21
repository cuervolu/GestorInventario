package com.cuervolu.gestioninventario.services.impl;

import com.cuervolu.gestioninventario.entities.UserEntity;
import com.cuervolu.gestioninventario.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository repository;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> userEntityOptional = repository.findByUsername(username);

    if (userEntityOptional.isEmpty()) {
      throw new UsernameNotFoundException(
          String.format("User with username %s not found", username));
    }
    UserEntity userEntity = userEntityOptional.get();

    List<GrantedAuthority> authorities = userEntity.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

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
