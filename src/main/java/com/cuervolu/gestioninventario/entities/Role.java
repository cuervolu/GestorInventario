package com.cuervolu.gestioninventario.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(unique = true)
  private UserRole name;

  @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"})
  @ManyToMany(mappedBy = "roles")
  private List<UserEntity> users;
  
}
