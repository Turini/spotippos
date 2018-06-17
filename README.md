# The legend of Spotippos

Helping bytes to find the house of their dreams.

## Technologies

JDK 10, VRaptor 4, Bean Validation 2, Maven 3.5+, Docker

## Building in your machine

To build Spotippos in your machine you just need to import it as a Maven project using your favorite IDE. You can also pack it by running:

```
mvn package
```

## Running Spotippos app

If you already have Java 10 and mvn installed, you can easily execute this app using:

```
mvn tomcat7:run
```

Otherwise, you'll probably prefer to build it using docker:

```
docker build -t spotippos .
```
and then run:

```
docker run -ti --rm -p 8080:8080 spotippos
```
