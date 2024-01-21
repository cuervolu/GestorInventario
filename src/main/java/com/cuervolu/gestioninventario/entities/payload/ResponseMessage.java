package com.cuervolu.gestioninventario.entities.payload;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ResponseMessage implements Serializable {
  private String message;
  private Object object;
}
