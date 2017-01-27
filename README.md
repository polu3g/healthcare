# Enterprise Microservices Framework using Spring Boot

A High-quality pervasive Microservices framework based on Spring Boot restful application that uses JPA via hibernate to MS SQLServer and  as well remote mongodb.. This been extended to connect to AMQP(cloudamqp) and a viable Websocket connection.

## Getting Started

The project will help you to understand how various libraries are used to build a quick microservice-app that can set to perform following features for you:

1. How to setup a gradle project for a microservice
2. To acquire libraries for JPA , Mongodb, AMQP(MQ), Websocket , Rest endpoints
3. How to configure hibernate including level-1 caching
4. how to add external jars
5. how to write Unit testcases
6. how to plum to a bigger corporate framework


**Architecture paradigm**


<p align="center">
  <img src="ss-2.jpg"/>
</p>

**Big picture**
<p align="center">
  <img src="ss-3.jpg"/>
</p>

### Prerequisites

Since, I'm using Gradle so less xmls -:) It would download all requisite libraries from maven. whatever required extra can put within lib folder. Gradle doesnt require an IDE.. 
You still can use Eclipse or other as per your zeal.. 

### Installing

You need to import the project from https://github.com/polu3g/healthcare.git

build it with gradle. during build it would go for testcases also.. so if your libraries are correct and database properties are fine then Voilla -:)

few lines from **application.properties**

```
spring.datasource.url = jdbc:sqlserver://localhost:1433;database=JVDB;integratedSecurity=true
spring.datasource.driverClassName =com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.SQLServerDialect
#Connecting to a MongoDB database
spring.data.mongodb.uri=mongodb://u12:ur21@d899.mlab.com:27439/t122
```
You need to note the things carefully here:

1. Springboot uses hibernate dialect to connect to sql server
2. the driver class SQLServerDriver is loaded from lib\sqljdbc4-2.0.jar
3. it has mongodb driver loaded from :
spring-boot-starter-data-mongodb
4. ddl-auto will update the sqlserver DDL to & fro

Now, let me tell you about enabling caching at level #1:

1. Annotation @EnableCaching with HealthcareApplication.java. This is to enable the preprocessors to analyse & discover the further cachables from the code.
2. @Cacheable in SQLSrvRepository.java annotate those methods need to be cached
3. @Cacheable at SudentEntity also for the instance level
4. Thats it!! level #1 cache is enabled..

you can see sql trace multiple findall() calls now produce a Single trace at log..

Now your IDE will look as below. Gradle tasks are shown at rightside. Choose to run build first!

<p align="center">
  <img src="ss-1.jpg"/>
</p>

We also have a RestController here as @RestController annotation. Refer the class to see what it has..

```
@RequestMapping("/findall")
    public List findall(@RequestParam(value="name", defaultValue="World") String name) {
		List<StudentEntity> pupils = (ArrayList<StudentEntity>) sqlSrvRepository.findAll();
        return pupils;
    }
	
	@RequestMapping("/findallmongo")
    public List findallmongo(@RequestParam(value="name", defaultValue="World") String name) {
		List<Customer> pupils = (ArrayList<Customer>) mongoRepository.findAll();
        return pupils;
    }
```
Observe that a single controller handling bothe RDBMS & NO-SQL database seemlessly -: ) Isn't that great buddy !

 

## Running the tests

if you run a build, it kicks HealthcareApplicationTests & other tests classes

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Spingboot Gradle](http://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-gradle-plugin.html) - The dependency management framework used for Springboot
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/riv/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Riv Neil** - *Initial work* - [riv](https://github.com/riv)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
