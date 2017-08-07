# Spring webpage

By: Mateo Murillo Penagos - mmurill5@eafit.edu.co

# Descripción de aplicación

Aplicacion web que permite administracion de los metadatos de canciones (CRUD), creacion y modificacion de usuarios y un sistema de control del contenido publico y privado de las canciones creadas.

El desarrollo cubre los siguientes aspectos:

* Aplicación del patron MVC a una aplicación Web
* Uso de un framework backend moderno -> Spring
* Configuración de ambientes: Desarrollo, Pruebas y Producción.

# 1. Análisis

## 1.1 Requisitos funcionales:

1. Crear canciones.
2. Buscar canciones por su titulo
3. Borrar canciones (Unicamente el propietario de la cancion puede eliminarla)
4. Listar todos las canciones PUBLICSA que existan en la base de datos en la página welcome o index
4.1. Listar las ultimas canciones agregadas y publicas en la pagina welcome o index (Maximo 5 canciones)
5. Listar todas las canciones publicas y privadas del usuario logueado en la pagina collection
6. Ver el detalle de las canciones (En el detalle solo el dueño de la cancion podra eliminar o editar la misma)
7. Crear usuarios
8. Loguearse y crear una sesion en el explorador web
9. Desloguearse y cerrar la sesion en el explorador web
10. Modificar el perfil del usuario logueado

## 1.2 Definición de tecnología de desarrollo y despliegue para la aplicación:

* Lenguaje de Programación: Java
* Framework web backend: Spring - SpringBoot
* Framework web frontend: Thymeleaf para el uso de templates html dinamicos como vistas
* Base de datos: PostgreSQL
* Web Server: Apache Tomcat Embebido

# 2. Desarrollo

Se importaron todas las librerias y dependencias con Maven

# 3. Diseño:

## 3.1 Modelo de datos:

Esto modelo de datos es usado en la API generada que reconoce el formato Json

song:

[
  {
    "title": "Muro's Song",       
    "albumName": "Best Album",    //puede ser null
    "artistName": "MuroDJ",       //puede ser null
    "genre": "House",             //puede ser null
    "duration": 180,
    "year": 2017,                 //puede ser null
    "owner":"mamup11",            //El usuario al que le pertenece la cancion, si se usara la API el usuario que se debe colocar es mamup11 que es el usuario general, pero este campo no puede ir vacio y debe contener un usuario previamente creado en la base de datos
    "publicContent":"V"           //'P' para indicar contenido publico  y  'V' para indicar contenido privado
  }
]

## 3.2 Servicios Web

IMPORTANTE:
--------------------------------------------------------------------------------------
La aplicacion tiene una API para creacion de canciones por medio de un servicio REST que admite formato Json, los otros servicios web trabajaran con templates y requeriran cookies para manejar la seguridad en los usuarios logueados, por lo que no es factible usarlas para probar.
Los servicios de la API que reciben Json no tienen un mapeo de errores por lo que en caso de error no cuenta con una facil deteccion del problema
--------------------------------------------------------------------------------------

Servicio: Encuentra y devuelve todas las canciones en la base de datos en formato Json
URI = "/findAllSong", method = GET

Servicio: Recibe una lista de canciones como la descrita en el punto 3.1 de este documento y la almacena en la base de datos
URI = "/songs", method = POST

Servicio: Retorna un template generado con las canciones publicas y las ultimas canciones agregadas a la base de datos
URI = "/", method = GET

Servicio: Retorna un template generado con las canciones que le pertenecen al usuario logueado y las añadidas recientemente
URI = "/collection", method = get

Servicio: Retorna el template para guardar canciones
URI = "/song", method = GET

Service: Recibe una cancion para almacenar mas el usuario logueado
URI = "/song", method = POST

Service: Retorna el template de la vista de una sola cancion (Si el usuario logueado es el propietario agrega las opciones de edicion y eliminacion)
URI = "/song/{cod}", method = GET

Service: Elimina una cancion de la base de datos
URI = "/song/{cod}", method = DELETE)

Service: Retorna el template para la modificacion de canciones
URI = "/song/{cod}", method = PUT

Service: Modifica una cancion en la base de datos
URI = "/song/{cod}", method = PATCH

Service: Retorna el template para la busqueda de canciones
URI = "/search", method = GET

Service: Recibe el titulo y retorna un template con las canciones encontradas
URI = "/search", method = POST

Service: Retorna el template para logueo
URI = "/login", method = GET

Service: Retorna el template para registro
URI = "/register", method = GET

Service: Almacena el usuario en la base de datos
URI = "/register", method = POST

Service: Retorna el template de modificacion de perfil
URI = "/profile", method = GET

Service: Modifica el perfil en la base de datos
URI = "/profile", method = PATCH

# 4. Despligue en un Servidor Centos 7.x en el DCA


## se instala Maven en la maquina

source: http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz

      user1$ wget http://www-eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
      user1$ sudo tar xzf apache-maven-3.3.9-bin.tar.gz
      user1$ sudo ln -s apache-maven-3.3.9 maven

## se instala el servidor PostgreSQL

como root:

      user1$ sudo yum install postgresql-server postgresql-contrib

ponerlo a correr:

      user1$ sudo systemctl start postgresql
      user1$ sudo systemctl enable postgresql


lo instala de los repositorios propios de Centos.

Abrir el puerto 8080

      user1$ sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent
      user1$ sudo firewall-cmd --reload


ponerlo como un servicio, para cuando baje y suba el sistema:    

      Añadir en /etc/rc.d/rc.local el comando: java -jar /home/mmurill5/server/webpage-0.0.1-SNAPSHOT.jar

          user1$ sudo reboot      


# 5. Despliege en Heroku

Queda en producción en:

            https://afternoon-ocean-97432.herokuapp.com/

/////

@20172            
