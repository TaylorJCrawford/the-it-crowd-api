# DropwizardTheITCrowdService

How to start the DropwizardTheITCrowdService application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/DropwizardTheITCrowd-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

### Using Cloud Instance:

- Alternative there is a cloud instance available that can be found at: `https://mgf63d3g3p.eu-west-1.awsapprunner.com`

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
=======
To see your applications health enter url `http://localhost:8081/healthcheck`. (This will only run on local versions)

Running Docker (Locally)
---

To build a docker image / container you require docker to be installed.
Then you can run the command below to build and run local container.
This should be run from the root of the repository.

```shell
  docker build -t the_it_crowd_api . && docker run -p 8080:8080 -p 8081:8081 -it the_it_crowd_api
```
