# Payconiq Assignment - Rest Backend Application

This application provides getting a list of stocks, getting single stock, updating the price of single stock, creating a stock.


## Technologies used in the project

* JDK 1.8 (must be pre-installed).
* Maven for build automation.
* JUnit for unit testing.

## How to Build and Run the Project

This project uses Maven build system, you can build the project locally just by typing
the following in the console:

    mvn package

To run the application go to the directory '/target' and type the following in the console:

    java -jar rest-service-stock-1.0.0

## Running the tests

You can run the tests by typing the following in the console:

    mvn test

An example output is look like:

    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    [INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.693 s - in com.payconiq.StockControllerTests
    2018-03-28 13:36:10.369  INFO 10180 --- [       Thread-3] o.s.w.c.s.GenericWebApplicationContext   : Closing org.springframework.web.context.support.GenericWebApplicationContext@2cb4893b: startup date [Wed Mar 28 13:36:08 AST 2018]; root of context hierarchy
    [INFO] 
    [INFO] Results:
    [INFO] 
    [INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
    [INFO] 
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 5.902 s
    [INFO] Finished at: 2018-03-28T13:36:10+03:00
    [INFO] Final Memory: 27M/217M
    [INFO] ------------------------------------------------------------------------

### Break down into the tests

There is 1 test class: StockControllerTests

StockControllerTests tests the functionality of rest service.

####Author
Ahmet FILIZ

