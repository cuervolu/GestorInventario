package com.cuervolu.gestioninventario.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "users",
    uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic
  @Column(nullable = false, length = 50)
  private String username;

  private String firstName;
  private String lastName;

  private String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Column(columnDefinition = "boolean default true")
  private Boolean enabled = true;

  @Transient private boolean admin;

  @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})
  @ManyToMany
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
  private List<Role> roles;
}
