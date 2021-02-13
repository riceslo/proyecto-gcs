# Ejemplo GCS

Esta aplicación web está basada en la aplicación play-scala-slick-example.


# Compilación

Para compilar el proyecto usamos Vagrant. Desde la consola, ejecutamos el
comando:

    $ vagrant up
    $ vagrant ssh

La máquina virtual de Vagrant contiene todas las dependencias, incluído Docker,
para ejecutar los tests unitarios y los tests de integración.

Para descargar el código y compilarlo en la máquina de desarrollo provisionada hacemos los
comandos siguientes:

    $ git clone https://github.com/mundacho/proyecto-gcs.git
    $ cd proyecto-gcs
    $ sbt compile

# Lanzamiento de tests unitarios

Para correr los tests unitarios, desde la máquina de Vagrant ejecutamos los comandos
siguientes:

    $ sbt test

Esto construye el proyecto y lanza los tests unitarios.


# Lanzamiento de tests de integración

Para correr los tests de integración, primero nos conectamos desde otra consola
y procedemos a iniciar el servidor Docker, y lanzar la base de datos.

    $ sudo systemctl start docker
    $ sudo docker run -it -e POSTGRES_PASSWORD=gcs -e POSTGRES_USER=gcs -e POSTGRES_DB=gcs -p 127.0.0.1:5432:5432  postgres:9.5


Dejamos Docker corriendo en esa consola y nos conectamos desde otra consola. En
la nueva consola, procedemos a cambiar al directorio donde está el código y
ejecutamos el comando siguiente:

    $ sbt "project gcsAppIT" test

Esto lanzará los tests de integración, que requieren de la base de datos para
funcionar.

# Empaquetar

Para empaquetar en RPM debemos usar el comando siguiente:

    $ sbt rpm:packageBin
