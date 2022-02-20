## REST API para prueba técnica de Ingeneo.
 
### Tecnología
 
La aplicación es un REST API construida sobre la siguiente tecnología:
 
* Lenguaje de programación Java (version 11)
* Apache Maven (gestión de dependencias y construcción de ejecutables)
* Base de datos PostgreSQL.
* Spring (framework principal)
* Spring Boot (para facilitar el setup del proyecto y las configuraciones)
* Swagger 2.0 (documentación de endpoints)
* Heroku (para desplegar el demo en la siguiente url:[https://ingeneo-prueba.herokuapp.com/](https://ingeneo-prueba.herokuapp.com/).)

### Base de datos
 
Para la gestión de cambios de la base de datos se usan migraciones con
Flyaway, esto permite controlar los cambios en la estructura de forma más ordenada y desde el código fuente.
 
El diagrama ER de la base actualmente es el siguiente:

![Diagrama ER](diagrama_er.png)

### Endpoints

* /login, para la autenticación de los usuarios devuelve un token JWT.
* /users, administración de usuarios (solo permitido para usuarios con rol ADMIN)
* /clients, administración de clientes.
* /products, catálogo de productos.
* /unitMeasures, catálogo de unidades de medida.
* /warehouses, administración de puertos y/o bodegas.
* /deliveries, administración de planes de entrega.
 
Para un mayor detalle de los endpoints disponibles consultar la [documentación](https://ingeneo-prueba.herokuapp.com/swagger-ui/).

Para autenticarse dentro de la documentación colocar el token JWT de la siguiente manera:

![Swagger Auth](swagger_auth.jpg)

### Roles y Permisos

La aplicación tiene un sistema básico y simple de roles y permisos.
 
Existen dos roles: admin y user.
 
Los usuarios solo pueden tener un rol a la vez.
 
Un usuario con rol admin es el único que puede crear nuevos usuarios y administrar los existentes, durante la inicialización de la base de datos se crea un usuario admin original para facilitar estas tareas.
 
Los usuarios con rol user no pueden administrar los usuarios pero sí está permitido que gestionen todos los demás recursos (clients, products, etc.)

### Autenticación
 
A continuación se muestra un ejemplo (usando curl) para obtener un token JWT.

```
curl -i -X POST -H "Content-Type:application/json" -d '{  "email" : "admin@ingeneo.com.co", "password": "admin1234"}' https://ingeneo-prueba.herokuapp.com/login
```

Luego, el token JWT obtenido se usa para autenticarse y utilizar los demás endpoints.

```
curl -i -X POST -H "Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBpbmdlbmVvLmNvbS5jbyIsImV4cCI6MTY0NTMzNDM5N30.iYxoL-D9TZJN8Q2qzDFlWKLNFTNOnkReYKZkDDtAJTBB3W7Z1xfaSTGLon4UQOrHvNzYMHDKhb0MWE-ORlbHoA" -H "Content-Type:application/json" -d '{  "email" : "hugo@preba.com", "password": "123456", "role":"ROLE_USER"}' https://ingeneo-prueba.herokuapp.com/users
```