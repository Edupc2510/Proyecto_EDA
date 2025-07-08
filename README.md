# 📄 Sistema de Gestión de Trámite Documentario - Universidad de Lima

Este proyecto es un sistema desarrollado en Java (con GUI en Swing) que permite registrar, gestionar y consultar expedientes y trámites dentro de una oficina universitaria. Fue elaborado como parte del curso de Estructura de Datos Avanzada.

## 🚀 Características Principales

### ✅ Login Seguro
- Verificación de credenciales de usuario.
- Control de acceso con roles definidos.

### 📊 Menú Principal
- Acceso rápido a funcionalidades.
- Alerta automática de **expedientes pendientes**, ordenados por **prioridad y antigüedad** (gracias a estructuras TDA como listas circulares).
  
### 📝 Registro de Expediente
- Validación de todos los campos.
- Autocompletado inteligente por DNI:
  - Se recuperan automáticamente nombre, tipo de usuario, correo y teléfono si el interesado ya está registrado.
  - El nombre y tipo de usuario no pueden editarse, garantizando integridad de datos.

### 🔁 Registro de Movimiento
- Filtros por dependencia o búsqueda directa por DNI.
- Validaciones para evitar registros incompletos.
- Gestión de traslado de expedientes entre dependencias.

### ✅ Finalización de Trámite
- Filtros por dependencia o búsqueda por DNI.
- Validación del trámite pendiente antes de finalizar.
- Registro de documentos generados (separados por comas) en una lista ligada.

### 🔍 Consulta de Seguimiento
- Visualización en **dos tablas sincronizadas**:
  - Tabla 1: Selección de expediente.
  - Tabla 2: Historial de movimientos asociados.
- Solo se puede buscar por DNI para garantizar **privacidad de información**.

### 📚 Historial General
- Resumen general de todos los expedientes.
- Visualización directa del estado (Finalizado o En Proceso).
- Opción de ingresar un DNI para ver el historial detallado de un usuario específico.

## 🧠 Estructuras TDA Utilizadas

- **Lista Simple** → Para seguimiento de trámites por expediente.
- **Lista Doble** → Para navegación entre expedientes registrados.
- **Lista Circular** → Para alertas automáticas de expedientes pendientes.
- **Pilas** → Para manejo de historial individual del interesado.
- **Colas** → Por dependencia, gestionando el orden de atención de los expedientes.

## 👨‍💻 Repartición del Proyecto

**Eduardo Pantoja**  
→ Diseño del sistema y estructura general del proyecto, pruebas de integración y validaciones.  
→ Desarrollo de la interfaz gráfica orientada a usabilidad y apariencia moderna, incluyendo mejoras visuales y funciones como el autocompletado por DNI.

**Carlos Doig**  
→ Implementación de la interfaz gráfica, conexión entre pantallas y gestión de eventos.  
→ Control del flujo de operaciones y validaciones funcionales en la interfaz.

**Paola Butrón**  
→ Desarrollo de estructuras TDA personalizadas y clases base del sistema.  
→ Validaciones lógicas, pruebas de funcionamiento y retroalimentación basada en simulaciones reales.

## 💎 ¿Por qué es genial este sistema?

- ✨ Autocompletado por DNI para duplicar velocidad de ingreso.
- 🔁 Flujo de trámites trazable y completamente validado.
- 📬 Alertas automáticas para evitar olvidos.
- 🛡️ Seguridad en el acceso y en la visualización de información sensible.
- 💻 GUI amigable, clara y funcional.

## 🛠️ Tecnologías

- Java (JDK 17)
- Swing
- NetBeans
- Git & GitHub

---

¡Gracias por visitar nuestro proyecto! 😄
