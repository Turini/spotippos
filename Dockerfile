FROM openjdk:10-jdk

RUN apt-get -y update && apt-get install -y maven

WORKDIR /spotippos

ADD pom.xml /spotippos/pom.xml

ADD src /spotippos/src
RUN ["mvn", "clean", "package"]

EXPOSE 8080
CMD ["mvn", "clean", "tomcat7:run"]