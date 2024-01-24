package com.cuervolu.gestioninventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal de la aplicación que inicia el contexto de Spring Boot.
 *
 * <p>Esta clase utiliza la anotación {@code @SpringBootApplication}, que incluye automáticamente
 * configuraciones como {@code @Configuration}, {@code @EnableAutoConfiguration}, y
 * {@code @ComponentScan}. La aplicación se inicia llamando al método {@code
 * SpringApplication.run()} en el método {@code main}.
 *
 * <p>Este es el punto de entrada para la aplicación de gestión de inventario.
 *
 * @author cuervolu
 * @since 1.0
 * @version 1.0
 */
@SpringBootApplication
@EnableJpaAuditing
public class GestioninventarioApplication {

  /**
   * Método principal que inicia la aplicación de gestión de inventario.
   *
   * @param args Argumentos de línea de comandos.
   */
  public static void main(String[] args) {
    SpringApplication.run(GestioninventarioApplication.class, args);
  }
}
