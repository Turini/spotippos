# The legend of Spotippos

[![CircleCI](https://circleci.com/gh/Turini/spotippos.svg?style=svg)](https://circleci.com/gh/Turini/spotippos)
[![works badge](https://cdn.rawgit.com/nikku/works-on-my-machine/v0.2.0/badge.svg)](https://github.com/nikku/works-on-my-machine)

Helping bytes from the Goopple galaxy to find the house of their dreams.

## Technologies

JDK 10, VRaptor 4, Bean Validation 2, Maven 3.5+, Docker, CircleCI 2.0

## Building in your machine

To build Spotippos in your machine you just need to import it as a Maven project using your favorite IDE. You can also pack it by running:

```
mvn package
```

## Running Spotippos app

If you already have JDK 10 and mvn installed, you can easily execute this app using:

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
