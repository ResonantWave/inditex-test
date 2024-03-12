# Inditex Backend Test

## What
This is a simple microservice in charge of returning the current price for a specified product.

It uses an in-memory H2 database powered by Flyway to manage schemas. This way future updates to the database are a breeze.

As specified in the exercise instructions, there's two tables, `prices` and `brands`. You can check [the details here](src/main/resources/db/migration/V1__Create_tables_with_data.sql).

## Methodology
This exercise was developed with TDD from the beginning, developing in first place all the business test cases from the exercise.
This is reflected in the git history of this repo.

Even though a full Sonarqube instance hasn't been spin up for the exercise, Sonarlint has been used as a "light" alternative.

Also, with such low LOC count, test coverage is near 100%.

## How
Just execute `.\gradlew run`! Even though this is a microservice it is configured to be able to run as a standalone app for testing purposes.

With the app up and running you can send requests to the [/price](src/main/java/com/example/inditextest/infrastructure/api/PricesEndpoint.java) endpoint. No security has been implemented for this exercise.

To run the tests (unit and integration) run `.\gradlew test`.

## Asumptions taken
* Following the requirement of not implementing unnecessary features:
  * Brand only exists as a table in the database. It has not been brought as a JPA repo as it wouldn't be used anywhere.
  * Same with Product.
* All dates will comply with the ISO standard, specifically in the YYYY-MM-DDTHH:mm:ssZ form.
* Both PRODUCT_ID and PRICE_LIST have been defined as strings and not as integers. This can provide more flexibility if non-numeric ids are required.
* These two fields have also been defined with an arbitrary length of 10 and 2 respectively.
* Prices are being treated as BigDecimals to avoid rounding errors with doubles and other similar types.
* For the sake of the exercise data initialisation is done in the same script the tables are created.
* It is assumed that no two or more prices will conflict in priority within any given time range.

## Notes
* Swagger has been configured and it's available at http://localhost:8080/swagger-ui/index.html
* A Postman E2E collection is provided in [Inditex Prices.postman_collection.json](Inditex Prices.postman_collection.json) file.