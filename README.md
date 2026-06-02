# Plataforma de Auditoría de Código - Microservicio Backend (Java)

Este repositorio contiene el **Microservicio de Gestión y Persistencia** desarrollado en Java con Spring Boot para el proyecto "Plataforma de Auditoría de Código". Este componente actúa como orquestador del sistema, gestionando el registro de usuarios, la persistencia de los fragmentos de código analizados y conectando el Frontend con el microservicio de IA (Python).

## 🚀 Stack Tecnológico
* **Lenguaje:** Java 17
* **Framework:** Spring Boot (Jakarta EE)
* **Base de Datos:** MySQL / MariaDB (Relacional)
* **Gestor de Dependencias:** Maven (Wrapper incluido)
* **Entorno Local Recomendado:** XAMPP, Visual Studio Code

---

## ⚙️ Guía de Despliegue y Configuración Local

Para que este proyecto funcione en tu computadora local, no necesitas configurar código SQL manualmente. El sistema utiliza Hibernate/JPA para crear las tablas (`usuarios`, `auditorias`, `hallazgos`) de forma automática. Sin embargo, **debes instalar y preparar tu entorno siguiendo estos pasos exactos:**

### Paso 1: Configurar la Base de Datos con XAMPP
1. Descarga e instala XAMPP. Durante la instalación, asegúrate de dejar marcados **MySQL** y **phpMyAdmin** (puedes desmarcar Tomcat, FileZilla, etc.).
2. Abre el **XAMPP Control Panel**.
3. Haz clic en el botón **Start** en las filas de **Apache** y **MySQL** (el fondo debe ponerse de color verde).
4. Haz clic en el botón **Admin** en la fila de MySQL para abrir `phpMyAdmin` en tu navegador.
5. Ve a la pestaña **Bases de datos**, escribe exactamente `auditoria_db` en el campo de nombre y haz clic en **Crear**. *(La base de datos debe estar vacía, Spring Boot creará las tablas por ti)*.

### Paso 2: Instalar Java 17 (JDK)
1. Descarga **Eclipse Temurin JDK 17** desde la página oficial de Adoptium (distribución `.MSI` para Windows).
2. Abre el instalador y avanza hasta la pantalla de **Configuración Personalizada** (*Custom Setup*).
3. **¡MUY IMPORTANTE!** Haz clic en la cruz roja junto a la opción **Set JAVA_HOME variable** y selecciona **"Will be installed on local hard drive"** (Se instalará en el disco duro local).
4. Finaliza la instalación.

### Paso 3: Ejecutar el Proyecto
1. **Reinicia tu editor de código** (cierra por completo Visual Studio Code y vuelve a abrirlo) para que tu computadora detecte la nueva variable de entorno de Java.
2. Abre una terminal dentro de VS Code (`Terminal -> New Terminal`).
3. Revisar las credenciales de la base de datos en:
   ```bash
   backend-java/src/main/resources/application.properties
   ```
4. Navega hacia la carpeta del backend Java:
   ```bash
   cd backend-java
   ```
5. Ejecutar el proyecto
   ```bash
   ./mvnw spring-boot:run
   ```

### Paso 4: Crear Usuarios
1. Insertar el registro con username
2. Encriptar la contraseña usando el archivi
   ```
   backend-java/src/main/java/com/auditoria/backend/TestPassword.java
   ```
3. Correr el siguiente comando
   ```
   ./mvnw compile exec:java -Dexec.mainClass="com.auditoria.backend.TestPassword"
   ```
4. Ejemplo de lo que imprime
   ```
   [Password generada: $2a$10$C0Ce/LmIhs3kdGVw.Nl83.cJ8yaajYaYma0NLmUQkfxa6x8gWIlx6]
   ```
5. Próximos pasos / En desarrollo:

Módulo de Registro: Interfaz de alta de usuarios (Alumnos/Profesores) maquetada y lista para integración con el servicio de persistencia.
