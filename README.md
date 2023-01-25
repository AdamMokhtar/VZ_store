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

## Running 
After cloning the project locally cd to the docker directory

```bash
$ cd VZ_store/storeAPI/src/main/docker
```

Within the docker-compose file you can change the information e.g., changing the ports values.
Run the following command to 

```bash
$ docker-compose up
```

