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

This example is building with Lettuce 3: a scalable thread-safe Java RedisClient providing both synchronous and asynchronous connections for server.

- [Lettuce client 3](https://lettuce.io/lettuce-3/release/api/)

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

David Jiménez Anca : https://twitter.com/davidjimenezanc


### swagger.json

{
  "swagger" : "2.0",
  "info" : {
    "description" : "This is the API for CNN news",
    "version" : "1.0.0",
    "title" : "Swagger CNN news channels services API",
    "contact" : {
      "name" : "David Jimenez Anca",
      "url" : "https://github.com/davidjimenezanca",
      "email" : "dvdancca@gmail.com"
    },
    "license" : {
      "name" : "Apache 2.0",
      "url" : "http://www.apache.org/licenses/LICENSE-2.0.html"
    }
  },
  "host" : "http://localhost:8080",
  "basePath" : "/rest-cnn-news",
  "tags" : [ {
    "name" : "rest-cnn-news",
    "description" : "RESTful web services for searching in CNN channels news."
  } ],
  "paths" : {
    "/rest-cnn-news/search/getAll" : {
      "get" : {
        "tags" : [ "rest-cnn-news" ],
        "summary" : "getAll",
        "description" : "Search news in Redis database - Using Method GET; format result response for CHANNEL parameter",
        "operationId" : "getAll",
        "responses" : {
          "200" : {
            "description" : "List of CNN news names found in all channel"
          }
        }
      }
    },
    "/rest-cnn-news/search/linkContains" : {
      "get" : {
        "tags" : [ "rest-cnn-news" ],
        "summary" : "linkContains",
        "description" : "Search news in Redis database - Using Method GET; format result response for LINK parameter",
        "operationId" : "linkContains",
        "parameters" : [ {
          "name" : "link",
          "in" : "query",
          "description" : "Link key for News DB",
          "required" : true,
          "type" : "string"
        } ],
        "responses" : {
          "200" : {
            "description" : "List of CNN news names found that contain this link token"
          }
        }
      }
    },
    "/rest-cnn-news/search/searchByChannel" : {
      "get" : {
        "tags" : [ "rest-cnn-news" ],
        "summary" : "searchByChannel",
        "description" : "Search news in Redis database - Using Method GET; format result response for CHANNEL parameter",
        "operationId" : "searchByChannel",
        "parameters" : [ {
          "name" : "channel",
          "in" : "query",
          "description" : "Channel key for News DB",
          "required" : true,
          "type" : "string"
        } ],
        "responses" : {
          "200" : {
            "description" : "List of CNN news names found in this channel"
          }
        }
      }
    },
    "/rest-cnn-news/search/titleContains" : {
      "get" : {
        "tags" : [ "rest-cnn-news" ],
        "summary" : "titleContains",
        "description" : "Search news in Redis database - Using Method GET; format result response for TITLE parameter",
        "operationId" : "titleContains",
        "parameters" : [ {
          "name" : "title",
          "in" : "query",
          "description" : "Title key for News DB",
          "required" : true,
          "type" : "string"
        } ],
        "responses" : {
          "200" : {
            "description" : "List of CNN news names found that contain this title token"
          }
        }
      }
    }
  }
}


