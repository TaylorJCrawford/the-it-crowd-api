# DropwizardTheITCrowdService

How to start the DropwizardTheITCrowdService application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/DropwizardTheITCrowd-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


ENV Variables
--- 

To use DropwizardTheITCrowd API you will need to have the following environment variables


| Environment Variable | Description               |
|----------------------|---------------------------|
| DB_USERNAME          | Database Account Password |
| DB_PASSWORD          | Database Account Password |
| DB_HOST              | Database Hostname         |
| DB_NAME              | Database Schema Name      |


These have been used to connect to the aws database.

```shell
  export DB_USERNAME=<Add DB Username Here>  
  export DB_PASSWORD=<Add DB Password Here>  
  export DB_HOST=<Add DB Hostname Here>  
  export DB_NAME=<Add DB Schema Name Here>  
```

Run Unit Tests
---

To run unit tests run the following command:

```shell
mvn clean test
```

Run Integration Tests
---

To run integration tests run the following command:

- For this you will need to have your ENV variables set are pass into terminal (See ENV Variables Section).

```shell
mvn clean integration-test
```