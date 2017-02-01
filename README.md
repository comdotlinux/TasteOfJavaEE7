A Java EE 7 Project which uses the sakila database from Postgres Migrated to Mysql.

The Aim is to learn and document the Java EE implementation as I am learning it from java champion Adam Bien.

The Code is kept as neat as possible and done all in netbeans just to have a feel of the IDE.

For running the project in Docker.
1. Get the project in you local system and (of course) docker should be up and running.
2. Create somewhere a directory for storing the mysql database data. and update in movie-info/docker-compose.yml
	- The section is sakila: >> volumes: >> path before ":/var/lib/mysql"
	- Also update the location of mysql-sakila.sql (should be in the TasteOfJavaEE7/sakila directory)
3. Add the MYSQL_ROOT_PASSWORD, MYSQL_USER, MYSQL_PASSWORD to the sakila and phpmyadmin sections.
4. goto movieInfo directory and Edit the Dockerfile
	- Add the values of MYSQL_USER and MYSQL_PASSWORD to the --property user=xxxxxxxx:password=xxxxxxxxxx: respectively
4. docker build -t movie-info .    (run this command in the same directory)
5. docker-compose up (run this command in the same directory)

Now once done the application should be available at : dockerhostname / localhost depending upon your installation.
so for me on windows it is :
1. http://192.168.99.100:8080/movie-info/faces/actors.xhtml
2. http://192.168.99.100:8080/movie-info/faces/Films.xhtml

Rest Endpoints : 
1. http://192.168.99.100:8080/movie-info/resources/actors
2. http://192.168.99.100:8080/movie-info/resources/boundry-invocations/actor 
3. http://192.168.99.100:8080/movie-info/resources/boundry-invocations/actor-summary

== OLD == May be removed
The Project is deployed to Openshift at http://javaee7app-archlinux.rhcloud.com/index.xhtml which should run a fairly recent version of the application.

There is also a REST Interface that shall be augmented as and when possible.
All the rest interfaces start with resources. e.g. http://javaee7app-archlinux.rhcloud.com/resources/actors

Further examples : 
1. http://javaee7app-archlinux.rhcloud.com/resources/actors/11 to get actor number 11.


The OpenShift `jbossas` cartridge documentation can be found at:

https://github.com/openshift/origin-server/tree/master/cartridges/openshift-origin-cartridge-jbossas/README.md
