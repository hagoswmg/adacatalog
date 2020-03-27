# Getting Started

Clone this project, then import to your favourite IDE. Edit `application.properties` file and modify configuration parameters as needed. At the very least, you will need to provide database connection parameters.

You can run the project either using [spring cli](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-cli.html) or by running the main application. To use spring cli, go to the project base directory and get command prompt, then type the following command `mvn spring-boot: run`


### API Endpoints

Versioning is used for future upgrade as well as backward compatibility. For example, v1 could continue to work while developing v2, or some new features can be added in v2 for new clients while existing clients continue to use v1.

1. Entry Page (__GET__ request from recent list)
    - URI: http://localhost:8080/v1/adacatalog
    - Response Status Code: 200 
    - Response Content: A collection of recent list in JSON format
    
2. Status Drill Down Search (__GET__ request by confirmation code)
    - URI: http://localhost:8080/v1/adacatalog/{confirmationCode}
    - Response Status Code: 200
    - Response Content: Details of an item in JSON catalog
    
3. Search Page (__GET__ request from catalog by type)
    - URI:
        - http://localhost:8080/v1/adacatalog?type=All
        - http://localhost:8080/v1/adacatalog?type=GPID
        - http://localhost:8080/v1/adacatalog?type=MajorLabel
        - http://localhost:8080/v1/adacatalog?type=MarketingLabel
        - http://localhost:8080/v1/adacatalog?type=PresentationLabel
        - http://localhost:8080/v1/adacatalog?type=Upload
    - Response Status Code: 200 
    - Response Content: A collection of recent list in JSON format
    
4. Create Set (__POST__ request)
    - URI: http://localhost:8080/v1/adacatalog
    - Body 
    ```json
   {
       "name": "Name of Set",
       "company": "WMG",
       "comments": "A beautiful set",
       "payload" : "JSON Array from the selected set"
   }
    ```
   - Response Code: 201 - Created
   - Response Content : Response Entity with Confirmation ID

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)

