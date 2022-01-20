# Microservices experimental infrastructure
==========================
## Introduction
Basic project to experiment with microservices architecture. 
The following modules implemented:

## Downloader
A module to extract, load and transform data. The data is to be sent to ActiveMQ Artemis bus.
Module uses Spring Integration framework to work with data sources
Currently enabled datasources are:
 - Local file folder listener
 - SMB remote folder listener
## Webapp
Web application backend. Uses Spring Boot + JPA. Liquibase is used to update database. 
The module is also a consumer for ActiveMQ messages produced by Downloader.
Authentication is built using JWT stored in browser local storage.
Postgres is used as relational database 
## Frontend
Basic react admin for CRUD operations with entities like Users and others. 
Build on Marmelab react admin.
#Infrastructure
Docker containers required for development and deployment of the application
## Common
Services and tools shared across application modules
## API
DTOs used to transfer data between modules. At the moment ActiveMQ Artemis is used as a bus. 
