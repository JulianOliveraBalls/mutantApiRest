
---

# Proyecto de Detección de ADN Mutante

Este proyecto es una API REST desarrollada en **Java Spring Boot** que permite analizar cadenas de ADN para determinar si un individuo es mutante. Utiliza patrones de ADN para identificar secuencias repetidas de bases nitrogenadas (A, T, C, G) que están presentes en mutantes.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Uso de la API](#uso-de-la-api)
- [Base de Datos](#base-de-datos)
- [Tests](#tests)
- [Swagger](#swagger)
- [Contribución](#contribución)
- [Licencia](#licencia)

## Descripción

Este proyecto proporciona una API REST para detectar si una cadena de ADN corresponde a un mutante. El análisis se realiza mediante la búsqueda de secuencias repetidas de bases nitrogenadas en filas, columnas y diagonales de la matriz de ADN.

### ¿Cómo funciona?
- Si se encuentran **más de una** secuencia repetida de 4 bases nitrogenadas (A, T, C, G), el ADN pertenece a un mutante.
- El ADN es almacenado en una base de datos para evitar analizar la misma cadena más de una vez.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **H2 Database** (en memoria)
- **Spring Data JPA**
- **Lombok**
- **Swagger/OpenAPI** para la documentación de la API
- **Gradle** para la gestión de dependencias

## Instalación

### Requisitos Previos

- **Java 17**

### Clonar el repositorio

```bash
git clone https://github.com/tuusuario/mutant-dna-api.git
cd mutant-dna-api
```

### Compilar el proyecto

Ejecuta el siguiente comando para compilar el proyecto y descargar las dependencias:

```bash
gradle clean build
```

## Ejecución

Para ejecutar la aplicación localmente, utiliza el siguiente comando:

```bash
gradle spring-boot:run
```

Por defecto, la aplicación se ejecutará en el puerto **8081**. Puedes acceder a la API a través de la siguiente URL:

```
http://localhost:8081
```

## Uso de la API

### Endpoint para detectar mutantes

El endpoint principal permite enviar una cadena de ADN para ser analizada.

**URL:** `/mutant`

**Método:** `POST`

**Request Body:**

Debe enviarse un JSON con el formato siguiente, donde `dna` es un array de strings que representa la secuencia de ADN:

```json
{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```

**Respuestas:**

- **200 OK:** Si el ADN corresponde a un mutante.
- **403 Forbidden:** Si el ADN no corresponde a un mutante.

Ejemplo de respuesta exitosa:

```json
{
  "isMutant": true
}
```

### Endpoint de estadísticas

Se incluye un endpoint adicional para obtener estadísticas sobre el análisis de ADN.

**URL:** `/stats`

**Método:** `GET`

Este endpoint devuelve el número total de mutantes y humanos analizados, así como el ratio de mutantes.

```json
{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}
```

## Base de Datos

El proyecto utiliza **H2 Database** en modo en memoria para almacenar el ADN analizado. Puedes acceder a la consola de H2 para ver el contenido de la base de datos utilizando la siguiente URL:

```
http://localhost:8081/h2-console
```

**Credenciales:**

- JDBC URL: `jdbc:h2:mem:testback`
- Usuario: `sa`
- Contraseña: `back`

## Tests

El proyecto incluye pruebas unitarias para validar la funcionalidad del análisis de ADN y los servicios.

Para ejecutar los tests, utiliza el siguiente comando:

```bash
mvn test
```

## Swagger

El proyecto está documentado utilizando Swagger. Puedes acceder a la documentación interactiva de la API en la siguiente URL:

```
http://localhost:8081/swagger-ui.html
```

Aquí puedes ver y probar los endpoints directamente desde el navegador.

## Contribución

Si deseas contribuir a este proyecto:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios necesarios.
4. Haz un commit de tus cambios (`git commit -am 'Añadir nueva funcionalidad'`).
5. Sube los cambios (`git push origin feature/nueva-funcionalidad`).
6. Crea un nuevo **Pull Request**.

## Licencia

Este proyecto está bajo la Licencia MIT. Para más detalles, revisa el archivo [LICENSE](LICENSE).

---
