#  Sistema de Ventas - Java + SQLite

##  Descripción

Aplicación backend desarrollada en Java que permite gestionar productos, ventas y stock utilizando SQLite como base de datos.

El proyecto implementa un sistema completo de ventas con persistencia real, aplicando arquitectura en capas y buenas prácticas de desarrollo sin utilizar frameworks.

---

##  Funcionalidades

* Creación de ventas
* Agregado de productos con control de cantidades
* Validación de stock antes de confirmar la venta
* Actualización automática del stock
* Persistencia de ventas e ítems en base de datos
* Listado de ventas con sus respectivos productos
* Generación de tickets de compra

---

##  Arquitectura

El sistema está organizado en capas:

* **Controller** → Manejo del flujo del programa y entrada del usuario
* **Service** → Lógica de negocio y validaciones
* **Repository** → Acceso a datos mediante JDBC
* **Model** → Entidades del dominio
* **Exceptions** → Manejo de errores personalizados

Esta estructura permite mantener el código desacoplado, ordenado y preparado para escalar (por ejemplo, migrar a Spring Boot).

---

##  Base de Datos

Se utiliza SQLite como motor de base de datos.

El proyecto incluye un script de creación en:

```text
database/schema.sql
```

### Tablas principales:

* **productos**
* **ventas**
* **item_venta**

### Conceptos aplicados:

* Relaciones uno a muchos (Venta → Ítems)
* Normalización de datos
* Separación entre lógica de negocio y persistencia

---

##  Cómo ejecutar

### 1. Compilar

```bash
javac -cp "lib/sqlite-jdbc-3.14.2.jar" -d bin src/app/Main.java src/controller/*.java src/repository/*.java src/service/*.java src/model/*.java src/Exceptions/*.java src/database/*.java
```

---

### 2. Ejecutar

```bash
java -cp "bin;lib/*" app.Main
```

---

##  Estructura del proyecto

```
consoleRetro/
├── src/
│   ├── app/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── Exceptions/
│   └── database/
│
├── database/
│   └── schema.sql
│
├── lib/
│   └── sqlite-jdbc-3.14.2.jar
│
├── README.md
└── .gitignore
```

---

##  Aprendizajes

* Implementación de arquitectura en capas
* Uso de JDBC para conexión a base de datos
* Modelado de entidades y relaciones
* Manejo de lógica de negocio (stock, ventas, validaciones)
* Separación de responsabilidades
* Resolución de problemas reales de persistencia

---

##  Posibles mejoras

* Migración a Spring Boot (API REST)
* Integración con frontend (Angular / React)
* Autenticación de usuarios
* Reportes y métricas de ventas
* Mejoras en la gestión de inventario

---

##  Destacado

* Sistema completamente funcional sin frameworks
* Persistencia real en base de datos (no en memoria)
* Código organizado y mantenible
* Preparado para escalar a arquitectura web

---

##  Autor

Proyecto desarrollado como práctica avanzada de backend en Java, enfocado en resolver problemas reales y aplicar buenas prácticas de diseño.
