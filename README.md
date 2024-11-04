
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
### La API funciona en: https://mutantapirest.onrender.com/ 
#### En: https://mutantapirest.onrender.com/mutant a través del siguiente codigo se puede probar el uso de la misma(por consola)...
```bash
fetch('https://mutantapirest.onrender.com/mutant', {
    method: 'POST', // Método HTTP
    headers: {
        'Content-Type': 'application/json' // Tipo de contenido
    },
    body: JSON.stringify({ // Cuerpo de la solicitud
        dna: ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
    })
})
.then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json(); // Convertir la respuesta a JSON
})
.then(data => {
    console.log('Respuesta:', data); // Imprimir la respuesta en la consola
})
.catch(error => {
    console.error('Error:', error); // Manejar errores
});

```

### Endpoint de estadísticas

Se incluye un endpoint adicional para obtener estadísticas sobre el análisis de ADN.

**URL:** `https://mutantapirest.onrender.com/stats`

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
[http:/https://mutantapirest.onrender.com/h2-console]
```

## Tests

El proyecto incluye pruebas unitarias para validar la funcionalidad del análisis de ADN y los servicios.

Para ejecutar los tests, RUN a DnaServiceTest.java

---
