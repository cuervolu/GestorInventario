# GestionInventario - Aplicación de Gestión de Inventario para una Bodega de Hospital

Este proyecto es un sistema simple de gestión de inventario desarrollado con el propósito de estudio
sobre Spring Boot 3. La aplicación está diseñada para gestionar el inventario de una bodega de
hospital, permitiendo el seguimiento de productos, categorías y usuarios.

## Funcionalidades Principales

- **Gestión de Categorías:** Permite la creación, actualización y eliminación de categorías de
  productos.

- **Gestión de Productos:** Permite la gestión completa de productos, incluyendo la creación,
  actualización y eliminación. Los productos están asociados a categorías.

- **Gestión de Usuarios:** Se incluye un sistema básico de gestión de usuarios con roles (por
  ejemplo, ADMIN, USER). Solo los usuarios con el rol ADMIN pueden realizar ciertas operaciones.

## Tecnologías Utilizadas

- **Java:** Lenguaje de programación principal.

- **Spring Boot:** Framework de desarrollo para la construcción de aplicaciones Java basadas en el
  principio de "opinión sobre configuración".

- **Swagger:** Documentación interactiva de la API para facilitar el desarrollo y la prueba.

- **Base de Datos:** Se utiliza una base de datos (por ejemplo, PostgreSQL) para almacenar la
  información de la aplicación.
- Spring-DotEnv: Librería para la gestión de variables de entorno en un archivo `.env`.

## Requisitos del Sistema

- JDK 17 o superior instalado.

- Maven para la gestión de dependencias y la construcción del proyecto.

- Base de datos configurada (por ejemplo, PostgreSQL en Docker).

## Instrucciones de Configuración

1. Clona el repositorio: `git clone https://github.com/tuusuario/tuproyecto.git`

2. Configura la base de datos en `application.properties` y en un archivo `.env`.

3. Ejecuta la aplicación utilizando Maven: `mvn spring-boot:run`

4. Accede a la documentación de la API Swagger: `http://localhost:8080/swagger-ui.html`

## Contribuciones

¡Contribuciones y sugerencias son bienvenidas! Si encuentras algún problema o tienes una mejora, por
favor abre un issue.

## Licencia

Este proyecto está licenciado bajo la [Licencia MIT](LICENSE).
