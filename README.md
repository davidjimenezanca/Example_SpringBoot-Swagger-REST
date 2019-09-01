# Spring Boot Swagger-2 API framework with Redis DB

Example project of Swagger UI tools with Spring Data for Redis database.

REST services are built with Spring Boot framework.

# REST

REST has quickly become the de-facto standard for building web services on the web because they’re easy to build and easy to consume.

There’s a much larger discussion to be had about how REST fits in the world of microservices, but - for this repository - let’s just look at building RESTful services.

- [Spring Boot Rest](https://spring.io/guides/tutorials/bookmarks/)

# SWAGGER

Swagger is the world’s largest framework of API developer tools for the OpenAPI Specification(OAS), enabling development across the entire API lifecycle, from design and documentation, to test and deployment.

The Swagger framework is supported by a set of core tools for designing, building, and documenting RESTful APIs. All of these tools are free and open source projects available on GitHub.

### Swagger-UI

Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your Swagger specification, with the visual documentation making it easy for back end implementation and client side consumption.

In this repository you can find how Spring Boot Rest automatically generates Swagger-UI tool once project is running.

### Maven Swagger plugin

This plugin enables your Swagger-annotated project to generate Swagger specs and customizable, templated static documents during the maven build phase. Unlike swagger-core, swagger-maven-plugin does not actively serve the spec with the rest of the application; it generates the spec as a build artifact to be used in downstream Swagger tooling.

In this project you can find file "swagger.json" as generated one in 'swagger-ui/generated' folder. This is the document for this API resources.


# Redis

Redis is an open source (BSD licensed), in-memory data structure store, used as a database, cache and message broker. It supports data structures such as strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs and geospatial indexes with radius queries. 

Redis has built-in replication, Lua scripting, LRU eviction, transactions and different levels of on-disk persistence, and provides high availability via Redis Sentinel and automatic partitioning with Redis Cluster.

### Server

In this example we use Redis as a sever database in standalone mode.

### Java Redis Client / Lettuce.io

Lettuce is a fully non-blocking Redis client built with netty providing Reactive, Asynchronous and Synchronous Data Access. 

This example is building with Lettuce 5: a scalable thread-safe Java RedisClient providing both synchronous and asynchronous connections for server.

- [Lettuce client 5](https://lettuce.io/core/release/api/)

### Redis connection pool

Lettuce connections are designed to be thread-safe so one connection can be shared amongst multiple threads and Lettuce connections auto-reconnection by default. While connection pooling is not necessary in most cases it can be helpful in certain use cases.

So, you won't find any connection pool configuration in this repository.


## Prerequisites

You will need following to run this application:

- [Redis 4+](https://redis.io/download)
- [Java 8+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Maven 2+](https://maven.apache.org/)

NOTE ABOUT DATA MODEL: As data model is replicated from following Redis GitHub project: 

- [Spring Data for Redis](https://github.com/davidjimenezanca/Example_Spring-Data-Redis)

To work with this example you will need to run the previous project in order to store data sets at Redis server.

## Starting the application

As first step you will need to install and start a Redis DB.

Second you will need to store the Sets keys in Redis with the model mentioned in previous section.

For each Set key, the value is stored in XML-string format.

In this project client's DB configuration is done for a running local Redis server.

After local database requirements are done you can start this application using maven Spring Boot plugin: just typing:

 mvn spring-boot:run

Swagger UI application is available at:

 http://localhost:8080/swagger-ui.html

Rest endpoints are also available at each resource URI.


# See Also

- [Swagger](https://swagger.io)
- [Spring Boot](https://projects.spring.io/spring-boot)
- [Redis](https://redis.io)
- [Lettuce client](https://lettuce.io)

# Author

- David Jiménez Anca : https://twitter.com/davidjimenezanc
- mailto: dvdancca@gmail.com
- Upwork profile: https://www.upwork.com/fl/davidjimenez3


### swagger.json

A link to this file is also available at Gist: https://gist.github.com/davidjimenezanca/396cbff61bc0f9eb94a3227f46d93554

