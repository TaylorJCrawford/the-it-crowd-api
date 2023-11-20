FROM maven:latest

WORKDIR /code

COPY . /code

ARG DB_HOST
ARG DB_PASSWORD
ARG DB_USERNAME
ARG DB_NAME
ARG BASE_URL

ENV DB_HOST ${DB_HOST}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_NAME ${DB_NAME}
ENV BASE_URL ${BASE_URL}

RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "/code/target/DropwizardTheITCrowd-1.0-SNAPSHOT.jar", "server", "/code/config.yml"]