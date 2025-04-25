# MDD(Monde De Dev)

MDD is a social network for developers.It allows developers to subscribe to topics, and from their dashboard access posts related to the topics they are subscribed to. They can also write posts on any topic they choose and leave comments under the various posts.
The backend of the application is developed in JAVA with Spring Boot and the frontend with Angular.

## USING MDD
To use MDD, clone the project and follow these steps:

## <ins>BACK-END</ins>

### Prerequisites

* Java 17
* Mysql 8.x.x
* IDE like Intellij / Eclipse

### Database :

* After installing mysql, and setting your username and password, make sure your database server is up and running.

* The database will be created when the backend is launched and test data will be inserted into it.

You can use the following three users or create new ones:
* email : joe@gmail.com
* password : Test!1234


* email : david@gmail.com
* password : Test!1234


* email : christophe@gmail.com
* password : Test!1234

### Project setup :

* Open the project in your IDE. Open the application.properties file and edit the following lines:

```
#jwt secret key
jwt.secret=Your256BitSecretHere
```
:warning: use a 256-bit key, you can use an online key generator to do this.
```
#database
spring.datasource.url=jdbc:mysql://localhost:3306/mdd?createDatabaseIfNotExist=true
```
:warning: change the mysql port if it's not 3306.

### Run the project

* In your terminal, go to the "back" folder and follow the instructions :
<br></br>
  - Define two environment variables with these commands :
  ```
  export DB_USERNAME=your_username 
  ```
  ```
  export DB_PASSWORD=your_password 
  ```
  :warning: Replace "your_username" with your database username and "your_password" with your database password
<br></br>
- Launch the application with these commands:
  <br></br>
  :warning: Make sure the location of the jar file and modify it if necessary in the command
  ```
  mvn clean install
  java -jar target/mdd-api-0.0.1-SNAPSHOT.jar
  ```
  **or**
  ```
  mvn spring-boot:run
  ```

## <ins>FRONT-END</ins>

### Prerequisites

* Node 20.x.x
* Angular 18.x.x
* IDE VS code / Sublime text



### Run the project
* Open it in your IDE
* In your terminal place yourself in the "Front" directory
* install dependencies
    ```
    npm install
    ```
* start the development server
    ```
    ng serve
    ```
- Open your browser and navigate to:
    ```
    http://localhost:4200
    ```