# Inditex Backend Test

## What
This is a simple microservice in charge of returning the current price for a specified product.

It uses an in-memory H2 database powered by Flyway to manage schemas. This way future updates to the database are a breeze.

As specified in the exercise instructions, there's two tables, `prices` and `brands`. You can check [the details here](src/main/resources/db/migration/V1__Create_tables_with_data.sql).

## Methodology
This exercise was developed with TDD from the beginning, developing in first place all the business test cases from the exercise.
This is reflected in the git history of this repo.

## How
Just execute `.\gradlew run`! Even though this is a microservice it is configured to be able to run as a standalone app for testing purposes.

With the app up and running you can send requests to the [/price](src/main/java/com/example/inditextest/adapters/api/PricesEndpoint.java) endpoint. No security has been implemented for this exercise.

To run the tests (unit and integration) run `.\gradlew test`.

## Asumptions taken
* All dates will comply with the ISO standard, specifically in the YYYY-MM-DDTHH:mm:ssZ form.
* Both PRODUCT_ID and PRICE_LIST have been defined as strings and not as integers. This can provide more flexibility if non-numeric ids are required.
* These two fields have also been defined with an arbitrary length of 10 and 2 respectively.
* Prices are being treated as BigDecimals to avoid rounding errors with doubles and other similar types.
* For the sake of the exercise data initialisation is done in the same script the tables are created.