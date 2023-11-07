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

To see your applications health enter url `http://localhost:8081/healthcheck`. (This will only run on local versions)

Running Docker (Locally)
---

To build a docker image / container you require docker to be installed. 
Then you can run the command below to build and run local container. 
This should be run from the root of the repository.

```shell
  docker build -t theITCrowdAPI . && docker run -it theITCrowdAPI
```