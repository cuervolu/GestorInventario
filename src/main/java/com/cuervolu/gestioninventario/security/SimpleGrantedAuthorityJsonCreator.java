package com.cuervolu.gestioninventario.security;

import com.cuervolu.gestioninventario.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {
  @JsonCreator
  protected SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") UserRole role){}
}
