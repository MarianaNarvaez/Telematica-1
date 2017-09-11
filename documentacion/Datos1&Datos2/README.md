+# Spring webpage
+
+BY: Mariana Narvaez Berrio - mnarvae3@eafit.edu.co & Mateo Murillo Penagos - mmurill5@eafit.edu.co
+
+Datos1
+IP: 10.131.137.187
+
+Datos2
+IP: 10.131.137.218
+
+# Descripción de maquina Datos1
+
+En esta maquina se encuentra configurado NFS como servidor para actuar con la capa de servicios en las ip 10.131.137.185 y 10.131.137.216 
+como servidor y para actuar como cliente con la maquina 10.131.137.218.
+
+# Descripción de maquina Datos2
+
+En esta maquina se encuentra configurado NFS como servidor para actuar con la capa de servicios en las ip 10.131.137.185 y 10.131.137.216 
+como servidor y para actuar como cliente con la maquina 10.131.137.187.
+
+
+Ambas maquinas tiene rsync instalado, el cual sincroniza la información de los datos de ambas maquinas en conjunto  con cron que permite
+que esta operación se lleve a cabo cada 1 minuto.
+
+La capa de datos tiene una configuración maestro-esclavo donde la maquina 10.131.137.187 es el master y la 10.131.137.218 es el slave, 
+sin embargo en el momento en que el master falla el slave podra tomar su posición mientras este se recupera, posteriormente se invertiran los 
+papeles siendo el slave ahora el master y el master el slave.
+
+Ambas maquinas cuentan con una base de datos aparte en postgres, si una base de datos llega a fallar, el sistema se redigirira a la que
+esta en la otra maquina, en caso de que esta tambien falle, el sistema mostrara un errror.