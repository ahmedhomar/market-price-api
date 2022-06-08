package com.ahmedomar.marketpriceapi;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.StringReader;
import java.util.List;

public class CsvFeedHandler {


    private CsvFeedHandler() {
        // TODO document why this constructor is empty
    }

    //    Convert a String to a single Price
    public static Price getPriceFromCsv(String csvFile) {
        List<Price> prices = getPricesFromCsv(csvFile);
        return prices.get(0); //return the first price in the list
    }

// Convert CSV to List of prices
    public static List<Price> getPricesFromCsv(String csvFile) {
        CSVReader reader = new CSVReader(new StringReader(csvFile));
        return new CsvToBeanBuilder<Price>(reader)
                .withType(Price.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse(); //returns a list of prices
    }





}