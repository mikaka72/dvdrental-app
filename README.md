# DVD rental demo application 

This is a simple application with React / MaterialUI frontend and Spring boot rest api backend.

Underlying database is https://www.postgresqltutorial.com/postgresql-sample-database/. 
The easiest way to have it running on your local host with this docker 
image https://github.com/kedziorski/test-db-pg-dvdrental provided by https://github.com/kedziorski

Frontend is bootstrapped with Create React App https://github.com/facebook/create-react-app. 
Instructions to start the frontend can be found here: https://github.com/mikaka72/dvdrental-app/tree/main/frontend

Backend application is using maven. So to build the project just run:

#### mvn install 

on the project root.

You can start the backend rest service with 

#### mvn spring-boot:run

However there backend application requires a database which properties are set in https://github.com/mikaka72/dvdrental-app/blob/main/src/main/resources/application.properties

Those settings should work out of the box with the sample database docker container mentioned earlier (with "works on my mac" guarantee :) ) 
The mvn spring-boot:run does not trigger the database container to start, so it must be running prior the service is started.

If you want to use in memory h2 database just copy the https://github.com/mikaka72/dvdrental-app/blob/main/src/test/resources/application.properties to the /src/main/resources or do any other tweaks that you see fit.

Application can be also bundled to single spring boot application, there is a section on comments in pom.xml for that.

There is also a jib plugin configure in the pom.xml. It can be used to publish the application as an image to the azure container registry as described in https://docs.microsoft.com/en-us/azure/container-registry/container-registry-java-quickstart

Of course if you do not have both frontend and backend bundled to single application, only the backend will be included in the image. 

# Event driven model experiments with Kafka.

I have used this docker image https://hub.docker.com/r/spotify/kafka/
It has both the kafka and the zookeeper configured into the same image, so it is a easy starting point.

Download the docker imaage:

#### docker pull spotify/kafka

Create the docker container 

docker run -p 2181:2181 -p 9092:9092 --name kafka-docker-container --env ADVERTISED_HOST=127.0.0.1 --env ADVERTISED_PORT=9092 spotify/kafka

After the container is created use either the docker desktop to start / stop it or orchestrate it from the command line;

Here some useful commands: 

List  all containers:

####  docker container ls --all

Start container:

#### docker start [CONTAINER_NAME] or  [CONTAINER ID]

Check container logs:

#### docker logs [CONTAINER_NAME] or  [CONTAINER ID]

Tail the container logs during runtime:

#### docker logs [CONTAINER_NAME] or  [CONTAINER ID]--tail 1 --follow

Open bash to running container:

#### docker exec -it kafka-docker-container bash

In docker container bash you can configure topics in following folder:

#### cd /opt/kafka_2.11-0.10.1.0/

, create topic into kafka:

####  ./bin/kafka-topics.sh --create --zookeeper :2181 --replication-factor 1 --partitions 1 --topic [TOPIC_NAME]


List all the Kafka topics:

#### ./bin/kafka-topics.sh --list --zookeeper :2181

Describe Kafka topic: 

#### ./bin/kafka-topics.sh --zookeeper :2181 --describe --topic my-topic





