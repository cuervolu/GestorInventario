package com.cuervolu.gestioninventario.services;

import com.cuervolu.gestioninventario.controllers.dto.ChangePasswordDTO;
import com.cuervolu.gestioninventario.entities.UserEntity;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define operaciones de servicio para la gestión de usuarios en la aplicación.
 * Proporciona métodos para buscar usuarios por identificador, recuperar todos
 * los usuarios y guardar nuevos usuarios.
 *
 * <p>Esta interfaz abstrae las operaciones relacionadas con la gestión de usuarios, permitiendo una
 * implementación flexible en capas de servicios.</p>
 *
 * @author Cuervolu
 * @since 1.0.0
 */
public interface IUserService {
  /**
   * Recupera todos los usuarios registrados en la aplicación.
   *
   * @return Lista de usuarios registrados.
   */
  List<UserEntity> findAll();

  /**
   * Guarda un nuevo usuario en la base de datos.
   *
   * @param userEntity Usuario a guardar.
   * @return Usuario guardado.
   */
  UserEntity save(UserEntity userEntity);
  
  /**
   * Busca un usuario por su identificador único.
   *
   * @param id Identificador único del usuario.
   * @return Usuario encontrado, si existe.
   */
  Optional<UserEntity> findById(Long id);
  
  
  /**
   * Cambia la contraseña de un usuario.
   *
   * @param request Información de la nueva contraseña.
   * @param connectedUser Usuario conectado.
   */
  void changePassword(ChangePasswordDTO request, Principal connectedUser);
}
