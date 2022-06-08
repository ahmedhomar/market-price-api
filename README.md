# Market Price API

## Background

This is a RESTful API created in Spring Boot for a Market Price Handler Exercise.

It is a simple application that allows users to connect to a FX price feed of spot prices from the market. It modifies each price by applying a margin adjustment to it.
The new price is then made available to clients/components, so that they can get the latest price for an instrument whenever they request it.

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


## Features

•	Each message is a CSV string.
•	With an incoming price, process each with a margin (add commission) function, assume it is simply  -0.1% on bid, +0.1% on ask (subtract from bid, add to ask).
•	The adjusted price is published to a REST endpoint

## Testing
### Unit testing 

Test frameworks (JUnit and Mockito) are used to test the following:

1)	That the prices are being processed in sequence and that none are missing
2)	That only the latest price for a given instrument is stored (client should not be able to see older prices if a newer one has already been received)
3)	Validate each price to ensure that bid < ask and that the commission has been applied correctly

### Integration testing

### End-to-end testing

## Assumptions




