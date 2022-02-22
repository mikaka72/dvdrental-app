# DVD rental demo application 

This is a simple application with React / MaterialUI frontend and Spring boot rest api backend.

Underlying database is https://www.postgresqltutorial.com/postgresql-sample-database/. 
The easiest way to have it running on your local host with this docker 
image https://github.com/kedziorski/test-db-pg-dvdrental provided by https://github.com/kedziorski

Frontend is bootstrapped with Create React App https://github.com/facebook/create-react-app. 
Instructions to start the frontend can be found here: https://github.com/mikaka72/dvdrental-app/tree/main/frontend

Backend application is using maven. So to build the project just run:

#### mvn install 

on the project root

You can start the backend rest service with 

#### mvn spring-boot:run

However there backend application requires a database which properties are set in https://github.com/mikaka72/dvdrental-app/blob/main/src/main/resources/application.properties

Those settings should work out of the box with the sample database docker container mentioned earlier (with "works on my mac" guarantee :) ) 
The mvn spring-boot:run does not trigger the database container to start, so it must be running prior the service is started.

If you want to use in memory h2 database just copy the https://github.com/mikaka72/dvdrental-app/blob/main/src/test/resources/application.properties to the /src/main/resources or do any other tweaks that you see fit.

Application can be also bundled to single spring boot application, there is a section on comments in pom.xml for that.

There is also a jib plugin configure in the pom.xml. It can be used to publish the application as an image to the azure container registry as described in https://docs.microsoft.com/en-us/azure/container-registry/container-registry-java-quickstart

Of course if you do not have both frontend and backend bundeled to single application, only the backend will be included in the image. 
