package com.ahmedomar.marketpriceapi;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.StringReader;
import java.util.List;

public class CsvFeedHandler {


    //    Convert a String to a single Price
    public static Price getPriceFromCsv(String csvFile) throws IllegalStateException {
        List<Price> prices = new CsvToBeanBuilder<Price>(new StringReader(csvFile))
                .withType(Price.class) // <-- this is the important line
                .withIgnoreLeadingWhiteSpace(true) //ignore leading whitespace
                .build() //build the CSVReader
                .parse(); //parse the CSV file
        return prices.get(0); //return the first price in the list
    }

    // Convert CSV to List of prices
    public static List<Price> getPricesFromCsv(String csvFile) throws IllegalStateException {
        CSVReader reader = new CSVReader(new StringReader(csvFile));  //create a CSVReader
        return new CsvToBeanBuilder<Price>(reader) //create a CsvToBeanBuilder
                .withType(Price.class) // <-- this is the important line
                .withIgnoreLeadingWhiteSpace(true) //ignore leading whitespace
                .build() //build the CSVReader
                .parse(); //returns a list of prices
    }


}