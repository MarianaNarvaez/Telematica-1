# Spring webpage

BY: Mariana Narvaez Berrio - mnarvae3@eafit.edu.co & Mateo Murillo Penagos - mmurill5@eafit.edu.co

IP: 10.131.137.185 y 10.131.137.216

# Descripción de maquina

Esta maquina es un nodo servidor, el cual atendera las solicitudes de los clientes.

# Configuracion

* Instalar Java SE 1.7 o superior.
* Instalar Maven
* Clonar el repositorio con el codigo y ejecutar el .jar

ponerlo como un servicio, para cuando baje y suba el sistema:    

      Añadir en /etc/rc.d/rc.local el comando: java -jar /home/mmurill5/server/webpage-0.0.1-SNAPSHOT.jar

          user1$ sudo reboot      

# Capa de Datos

La aplicacion se conectara a la base de datos disponible, si la base de datos llega a caer, se intentara conectar a la siguiente en la lista. Si los 2 servidores de datos estan caidos, la aplicacion esperara hasta poderse conectar a alguno.

* Datos1: 10.131.137.187
* Datos2: 10.131.137.218