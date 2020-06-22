# Quarkus

Best source of knowledge is [the guides](https://quarkus.io/guides/)

Some quick summary:

## Create a project

```shell
mvn io.quarkus:quarkus-maven-plugin:1.5.2.Final:create \
    -DprojectGroupId=ibm.gse.eda \
    -DprojectArtifactId=app-name \
    -DclassName="ibm.gse.eda.GreetingResource" \
    -Dpath="/greeting"
cd app-name
```

## Package & run

Run with automatic compilation `./mvnw compile quarkus:dev`.

Can be packaged using `./mvnw clean package` or `./mvnw clean package -Pnative`

Start and overtide properties at runtime:

`java -Dquarkus.datasource.password=youshallnotpass -jar target/myapp-runner.jar`

for a native executable: `./target/myapp-runner -Dquarkus.datasource.password=youshallnotpass`

Can also use environment variables: Environment variables names are following the [conversion rules of Eclipse MicroProfile](https://github.com/eclipse/microprofile-config/blob/master/spec/src/main/asciidoc/configsources.asciidoc#default-configsources).

## Add capabilities

For example `./mvnw quarkus:add-extension -Dextensions="container-image-docker"`

### Docker build

`./mvnw clean package -Dquarkus.container-image.build=true` and push it to repository: `./mvnw clean package -Dquarkus.container-image.push=true`

## Generate configuration for the application

`./mvnw quarkus:generate-config` 


