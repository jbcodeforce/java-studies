FROM maven:3.6-jdk-11-slim AS build
LABEL maintainer="IBM Cloud Architecture Solution Engineering at IBM Cloud"
COPY . /usr/
RUN mvn -f /usr/pom.xml clean package

FROM openliberty/open-liberty:microProfile3-ubi-min
COPY src/main/liberty/config /opt/ol/wlp/usr/servers/defaultServer/

COPY  --from=build /usr/target/kafkaPlay-1.0-SNAPSHOT.war /config/dropins
RUN chown -R 1001:0 config/
USER 1001
RUN mvn liberty:run-server