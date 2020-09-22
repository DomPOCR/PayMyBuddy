<h2>Pay my Buddy</h2>


API REST application for managing payment. 

This app use SPRINT BOOT and the data are stored in a Mysql DB

<h2>Getting Started</h2>


* Endpoint : <http://localhost:8080/>
* Actuator : <http://localhost:8090/>


<h2>Prerequisites</h2>
What things you need to install the software and how to install them

* Java 1.8
* Maven 3.6.2
* Spring Boot 2.2.6
* My SQL 8.0

<h2>Installing</h2>

Run the sql command create.sql

User and password to access to the DB are stored in *Environnement Variables*;.

Use application.properties to change.

**spring.datasource.username=${P6_USER_SQL}**;

**spring.datasource.password=${P6_PWD_SQL}**; 

<h2>Running App</h2>
To start the application execute :

**java -jar PayMyBuddy-0.0.1-SNAPSHOT.jar**

<h2>Testing</h2>
mvn clean install mvn clean verify (generate tests and test report) mvn site (generate reportings)
<h2>Class Diagram</h2>


<img src = "https://github.com/DomPOCR/paymybuddy/blob/develop/P6_DiagrammeUML.png" title = "Diagramme UML">

<h2>Modèle Physique de Données</h2>


<img src = "https://github.com/DomPOCR/paymybuddy/blob/develop/P6_MPD.png" title = "Diagramme UML">