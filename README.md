# 📚 LiterAlura

**LiterAlura** es un proyecto desarrollado en **Java** con **Spring Boot** que permite construir un **Catálogo de Libros** interactivo.  
A través de una **API pública (Gutendex)**, el usuario puede buscar libros, guardar la información en una base de datos **PostgreSQL** y realizar diversas consultas desde la consola.

---

## 🎯 Objetivo del Proyecto

Desarrollar una aplicación de consola que:
- Permita **consultar libros** mediante una API externa.
- Almacene y gestione la información obtenida en una **base de datos relacional**.
- Brinde al usuario **interacción textual** (menú de opciones).
- Implemente al menos **5 opciones de interacción** relacionadas con libros y autores.

---

## 🧰 Tecnologías Utilizadas

- **Java JDK 17+**
- **Maven 4+**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **PostgreSQL 16+**
- **Jackson (para manejo de JSON)**
- **IntelliJ IDEA** (opcional)

---

## ⚙️ Configuración del Entorno

1. **Instala los siguientes programas:**
   - [Java JDK 17+](https://www.oracle.com/br/java/technologies/downloads/)
   - [Maven 4+](https://maven.apache.org/download.cgi)
   - [Spring Boot 3.2.3](https://start.spring.io/)
   - [PostgreSQL 16+](https://www.postgresql.org/download/)
   - [IntelliJ IDEA (opcional)](https://www.jetbrains.com/es-es/idea/download/)

2. **Configuración del proyecto con Spring Initializr:**
   - Tipo de proyecto: **Maven**
   - Lenguaje: **Java (17+)**
   - Packaging: **JAR**
   - Dependencias:
     - Spring Data JPA
     - PostgreSQL Driver

---

## 🌐 API Utilizada

El proyecto consume la API pública de **Gutendex**, que contiene información de más de 70.000 libros del Proyecto Gutenberg.

- **Endpoint base:** [https://gutendex.com/](https://gutendex.com/)
- **Repositorio oficial:** [https://github.com/garethbjohnson/gutendex](https://github.com/garethbjohnson/gutendex)
- **Formato de respuesta:** JSON
- **Clave de acceso:** No requerida

---

## 🧩 Fases del Desarrollo

### 1. Solicitud a la API

Uso de las clases de Java para comunicación HTTP:
- `HttpClient` → para enviar solicitudes.
- `HttpRequest` → para construir la petición.
- `HttpResponse` → para procesar la respuesta.

### 2. Análisis del JSON

Se utiliza la librería **Jackson** para mapear las respuestas JSON a clases Java:
- Dependencia: `com.fasterxml.jackson.core:jackson-core:2.16`
- Clases clave: `ObjectMapper`
- Anotaciones útiles: `@JsonIgnoreProperties`, `@JsonAlias`

### 3. Conversión de Datos

Los datos obtenidos se convierten a objetos Java:
- Clase `Libro` (Título, Autor, Idioma, Descargas)
- Clase `Autor` (Nombre, AñoNacimiento, AñoFallecimiento)
  
Implementación de métodos `getters`, `setters` y `toString()`.

### 4. Interacción con el Usuario

El método `main` implementa la interfaz `CommandLineRunner` y el método `run()` muestra un menú interactivo:
- Uso de `Scanner` para capturar la entrada del usuario.
- Validación de opciones y manejo de errores.

### 5. Consultas y Funcionalidades

**Funcionalidades obligatorias:**
1. Buscar libro por título.  
2. Listar todos los libros registrados.  
3. Listar todos los autores registrados.  
4. Listar autores vivos en un año determinado.  
5. Mostrar cantidad de libros por idioma.

**Detalles:**
- Un libro solo tiene un idioma y un autor (primer resultado de la API).
- Las consultas utilizan **Derived Queries** con Spring Data JPA.

### 6. Persistencia de Datos

Uso de **PostgreSQL** para almacenar libros y autores.  
Cada entidad tiene su propia tabla y se relacionan mediante un **ID**.

- Anotaciones: `@Entity`, `@Id`, `@GeneratedValue`
- Repositorios: `JpaRepository<Libro, Long>` y `JpaRepository<Autor, Long>`

---

## 📊 Consultas Implementadas

| Consulta | Descripción |
|-----------|-------------|
| 🔍 Buscar libro por título | Consulta la API Gutendex y guarda el primer resultado. |
| 📚 Listar libros | Muestra todos los libros guardados en la base. |
| 🧑‍💻 Listar autores | Muestra todos los autores registrados. |
| 🧓 Autores vivos por año | Filtra autores vivos en un año dado. |
| 🌍 Libros por idioma | Muestra la cantidad de libros según el idioma. |

---


## 🧠 Conceptos Clave Desarrollados

- Configuración de proyectos con **Spring Boot**.
- Consumo de APIs REST con **HttpClient**.
- Manejo de datos JSON con **Jackson**.
- Persistencia con **Spring Data JPA** y **PostgreSQL**.
- Interacción en consola con **Scanner**.
- Implementación de consultas con **Derived Queries**.
- Modularización y buenas prácticas en **Java**.

---

