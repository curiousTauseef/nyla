NYLA solutions contains a collection of projects/libraries to assist with Java based development.

## NYLA Solutions Global

[NYLA Solutions Global](https://github.com/nyla-solutions/nyla/tree/master/nyla.solutions.global) is an API provides support for basic application utilities (application configuration, 
data encryption, debugger, text processing and more). This library also has implementation for standard Gang Of Four design patterns and some custom patterns (ex: Command Pattern - COMMAS, searching - QueryService and more).

 
## NYLA DAO

[NYLA DAO](https://github.com/nyla-solutions/nyla/tree/master/nyla.solutions.dao) project is a simple Data Access Object (DAO) abstraction.
There are default implementations for the JDBC based access. 
This project expands on NYLA's Command pattern (a.k.a. COMMAS) for 
data access. This project implements NYLA's Query Service pattern for database based searches. The object nyla.solutions.dao.SQL is one of the most useful objects as it provides a simple interface to connect and perform JDBC data access (similar to the Spring Data JDBC Template API).

# NYLA Office

[NYLA Office](https://github.com/nyla-solutions/nyla/tree/master/nyla.solutions.office) provides utilities for creating Excel, PDF and open office documents.


## NYLA Web

[NYLA Web](https://github.com/nyla-solutions/nyla/tree/master/nyla.solutions.global.web)  project expands on NYLA's Command pattern (a.k.a. COMMAS) for building REST/web services. It implements its own Model View Controller pattern for pure JEE or web containers (ex: Jetty) MicroServices. This project provides NYLA's Query Service pattern for searches abstracted through web services.

## NYLA Solutions DAO Spring Batch

[NYLA Solutions DAO Spring Batch](https://github.com/nyla-solutions/nyla/tree/master/nyla.solutions.dao.spring.batch)  project provides password encryption, error Listener for email notifications and other utilities for the spring batch framework.


