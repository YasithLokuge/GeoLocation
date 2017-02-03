# Geo Location

[Java Coding Challenge]

### Prerequisites

* JAVA-8
* Maven 3.0.5 or above

### Before you begin

###### Obtain an API key to use Google GEO Location API using the following link:
https://developers.google.com/maps/documentation/timezone/start#get-a-key
###### Edit `deploy/config.yml` and enter the obtained api key

` APIkey: xxxxxxxxxxxxxx-xxxxxxxxxxxx `

### Installation Instructions

```
mvn clean install
```

###### `if you haven't changed the api key, the above command will fail due to the failure of unit tests. You can skip the tests and build the jar using the following command`

```
mvn clean install -Dmaven.test.skip=true
```

### How to Execute

```
java -jar target/GeoLocation-1.0.jar [configYamlFile] [inputFile] [outputFile]
```

Ex:

```
java -jar target/GeoLocation-1.0.jar deploy/config.yml deploy/input.txt deploy/output.txt
```