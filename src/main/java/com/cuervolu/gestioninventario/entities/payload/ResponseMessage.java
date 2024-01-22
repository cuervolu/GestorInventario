package com.cuervolu.gestioninventario.entities.payload;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Clase que representa un mensaje de respuesta genérico del sistema.
 *
 * <p>Contiene un mensaje descriptivo y un objeto asociado.</p>
 *
 * @author Cuervolu
 * @since 1.0.0
 */
@Data
@Builder
@ToString
public class ResponseMessage implements Serializable {
  private String message;
  private Object object;
}
