[![Build Status](https://travis-ci.com/thisarattr/profit-calculator.svg?branch=master)](https://travis-ci.com/thisarattr/profit-calculator)

# Profit Calculator App

This is a simple console application to calculate maximum profit, a person can gain/loose based on the provided share price for every minute.

The efficiency of the algorithm is `O(n)`, which mean this will only run once through the array. 

## Dependencies
* java8
* Google Guice 4.2
* Log4j2/ Slf4j2 2.13.0
* jUnit 4.14 / Mockito 3.0

## Assumption
* One can buy a stock at time[0] and sell at time[1], because there is 1 minute time difference.
* As per the example given (all small numbers), could have used integer type to represent stock price, but price should not be whole number in real life so best type to handle price, BigDecimal is used.
* profit could be negative when stock prices are falling during that time. Thus, maximum profit will be the minimum loss, considering one purchase and one sale has been done in that period.
 
 
## Build and run
Gradle is used as build tool and [travis](https://travis-ci.com/thisarattr/profit-calculator) has been configured for continuous integration. 

##### Console App
This will compile, checkstyle and run all the tests.  
`./gradlew clean build`

Test and checkstyle reports can be found,

Test report --> `$projectDir/build/reports/tests/test/index.html`

Checkstyle report --> `$projectDir/build/reports/checkstyle/main.html`  

This will package executable fat jar which can be run with below command.

`./gradlew jar`  



Artifacts can be located at `$projectDir/build/libs/profit-calculator-1.0.jar`, and below command will output the calculated max profit into console. `stock_proce_input` ia the program args, which is space separated list of numbers.

`java -jar $projectDir/build/libs/profit-calculator-1.0.jar <stock_price_input>`  
`java -jar $projectDir/build/libs/profit-calculator-1.0.jar 10 7 5 8 11 9`



Logs can be found at `$runLocation/logs/profit-calculator_2020-xx-xx.log`