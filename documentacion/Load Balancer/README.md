# Spring webpage

BY: Mariana Narvaez Berrio - mnarvae3@eafit.edu.co & Mateo Murillo Penagos - mmurill5@eafit.edu.co

IP: 10.131.137.162

# Descripción de maquina

Esta maquina es la maquina que actua como balanceador de cargas, tiene haProxy instalado y configurado para tener los servidores de logica listados; de modo que sepa a cual le enviara solicitudes y tener un respaldo si llega a caerse uno.

# Capa de Servicio

* Server1: 10.131.137.185
* Server2: 10.131.137.216

# Pasos para configurar haproxy

* Intalación:

    Prerequisitos:

      sudo yum install gcc pcre-static pcre-devel -y

    Haproxy:

      wget https://www.haproxy.org/download/1.7/src/haproxy-1.7.8.tar.gz -O ~/haproxy.tar.gz
      
    Descomprimir los archivos
    
      tar xzvf ~/haproxy.tar.gz -C ~/
      
    Compilar
      
      make TARGET=linux2628

    Instalar el programa
    
      sudo make install    
      
* Configuración de haproxy
      
      Añadir directorios:
      
        sudo mkdir -p /etc/haproxy
        sudo mkdir -p /var/lib/haproxy 
        sudo touch /var/lib/haproxy/stats
        
      Crear link simbolico
      
        sudo ln -s /usr/local/sbin/haproxy /usr/sbin/haproxy
        
      Iniciar haproxy al enceder el nodo
      
        sudo cp ~/haproxy-1.7.8/examples/haproxy.init /etc/init.d/haproxy
        sudo chmod 755 /etc/init.d/haproxy  
        sudo systemctl daemon-reload
        
        sudo chkconfig haproxy on
        
        sudo chkconfig haproxy on
       
        sudo firewall-cmd --permanent --zone=public --add-service=http
        sudo firewall-cmd --permanent --zone=public --add-port=8181/tcp
        sudo firewall-cmd --reload
