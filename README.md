# Sistema de Ventas en Java

## Descripción

Este proyecto es un sistema de ventas hecho en Java como práctica backend.
La idea fue simular un flujo real: manejar productos, realizar ventas y generar un ticket final.

---

## Cómo funciona

El sistema permite:

* Crear y gestionar productos
* Iniciar una venta
* Agregar y eliminar productos de un carrito
* Validar stock antes de confirmar
* Finalizar la venta
* Generar un ticket y guardarlo en archivo

---

## Estructura

Está organizado en capas para mantener el código ordenado:

* Model → entidades del sistema
* Repository → almacenamiento en memoria
* Controller → lógica de negocio
* Service → generación de ticket
* App → menú por consola

---

## Decisiones

* Separé responsabilidades para evitar mezclar lógica con la UI
* Usé repositorios en memoria como primera versión (pensado para migrar a DB)
* Centralicé la generación del ticket en un service reutilizable

---

## Estado actual

El sistema funciona completo en consola:

* Flujo de venta cerrado
* Validaciones básicas
* Tickets generados correctamente
* Historial de ventas en memoria

---

## Próximos pasos

* Persistencia con base de datos
* Migración a Spring Boot
* Exponer API REST

---

## Notas

Este proyecto forma parte de mi formación como desarrollador backend.
La idea es seguir iterándolo y llevarlo a una versión más cercana a producción.
