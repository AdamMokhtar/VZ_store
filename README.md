# VZ_store
VZ store is a basic SpringBoot API that gives the user the option to create and retrieve an order.

The application uses Postgres DB and SpringBoot framework.

The application also includes an openapi file swagger and a postman collection.
 The two files can be found under the following path.Cancel changes

```
VZ_store/storeAPI/src/main/resources/static
```
## Requirements

* Docker
* docker-compose
* Maven

## Running 
After cloning the project locally
* option 1

cd to the docker directory
```bash
$ cd VZ_store/storeAPI/src/main/docker
```
Within the docker-compose file you can change the information e.g., changing the ports values.
Run the following command

```bash
$ docker-compose up
```


* option 2

By getting [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html), as well hosting the postgres DB on docker

The following to run the DB

```bash
$ docker run --name some-postgres -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres
```

 Using Maven run the following

```shell
mvn spring-boot:run
```



 
