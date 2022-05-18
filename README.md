# eventAdvisor - TripAd***or to Events

## Running the app

```shell
docker-compose up
./gradlew clean bootRun
```

## OpenApi
http://localhost:8080/swagger-ui/index.html#/

#### MISSING <-> If Had more time

* improved api docs
* stress test from jmeter
* mutation tests
* integration tests
* unit tests
* code coverage from jacoco metrics
* k8s files
* error encapsulation with DTO for better handling
* geolocation based solutions
* splunk-ready logs

#### Tech options

* batch solution to fetch remote that instead of inner project. picked a 4 hours window to update current database.
