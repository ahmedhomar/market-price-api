# Market Price API

### Background
This is a simple price handler that reads prices from a Csv file and modifies each price by applying a margin adjustment to it.
The new price is then made available to clients/components, so that they can get the latest price for an instrument whenever they request it.

The code is presented using the following technologies:

- Maven
- Spring-Boot 
- Java 
- JUnit
- Mockito
- MySQL
- Spring-Data-JPA

### Features
The following features are implemented:
- Model 
- Controller
- Service
- Repository
- Integration Test
- Unit Test
- Documentation

The Model for this API is Price, which consists of unique id, instrument name, bid, ask and timestamp. From the brief for the exercise, I have assumed that 'bid' means the sell price and that 'ask' is the buy price (which is higher than the bid).
The market price feed  is provided in CSV format line by line for EUR/USD, GBP/USD and EUR/JPY. For example:

106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001 

…

107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002 

…

108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002

…

109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100

…

110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110


•	Each message is a CSV string.
•	Each incoming price is processed with a margin (commission) function, (-0.1% on bid, +0.1% on ask, subtract from bid, add to ask).
•	The adjusted price is published to a REST endpoint

### Testing

#### Unit testing 

Test frameworks (JUnit and Mockito) are used to test the following:

1)	That the prices are being processed in sequence and that none are missing
2)	That only the latest price for a given instrument is stored (client should not be able to see older prices if a newer one has already been received)
3)	Validate each price to ensure that bid < ask and that the commission has been applied correctly

#### Integration testing
Functional and integration tests were performed using Postman and @SpringBootTest

#### A strategy for end-to-end testing
One strategy for implementing end-to-end testing for this API and its UI would be to use the open source automation server, Jenkins. 
In the Jenkins instance we could run E2E tests with Cypress (browser-based test automation) to validate interactions with web pages 
and with Newman (the command-line test runner for Postman), to validate HTTP APIs.

#### Assumptions
Below is a list of assumptions made in the code:

- the api will be called from a client
- the API is only accepting prices in CSV format
- the CSV file is in the correct format
- the API will connect to a frontend UI to show the latest price to a real user
- the prices are being published to a REST endpoint
- the prices are being published to a database
- bid means sell price and ask is the buy price
- the prices are being read from a CSV file
- the FX price feed only consists fo the following spots:  EUR/USD, EUR/JPY and GBP/USD.
- the commission is -0.1% on bid and +0.1% on ask








