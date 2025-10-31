# chatbot proyectto integrador

Este repositorio es el backend del chatbot, aquí se explicará la estructura y qué objetivos tiene el chatbot.

# Objetivo
El objetivo del chatbot es mostrar preguntas, respuestas y recibir sugerencias por parte de los usuarios


# herramientas utilizadas 
* El proyecto usa la versión LTS 21.0.6 de java
* El framework utilizado es spring boot 3.5.6
* Docker
* Junit 5

  
# Estructura y explicación 
La estructura es DDD, se cuenta con paquetes para cada capa de las clases (entidades, Dto, repositorios, servicio, controladores, tests y super clases)
* Entidades: Representan las tablas de la base de datos
* Dto: Es un intermediario para no exponer las entidades directamente
* Repositorios: administran la información de la base de datos (con consultas)
* Servicio: funciones que tienen la lógica de negocio
* Controladores: administración de los endpoints y respuestas al cliente


¿Cómo se ejecuta?
1. Hay que tener docker instalado
2. Crea el archivo .env y reemplaza los valores de .env-ejemplo
3. abre el proyecto en la terminal y escribe "docker compose up"

¿Cuál es la url del proyecto?

link página: http://localhost:443/api/  + sección que quieras ver

ejem: http://localhost:443/api/categorias

Los endpoints disponibles se miran en el swagger:

link documentación swagger: http://localhost:443/swagger-ui/index.html 



