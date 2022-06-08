package com.ahmedomar.marketpriceapi;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

public class CsvFeedHandler {

//    Convert a String to a single Price

    public static Price convertCsvToPrice(String csv) throws IllegalStateException, FileNotFoundException {
        List<Price> prices = new CsvToBeanBuilder(new StringReader(csv)).withType(Price.class).build().parse();

        return prices.get(0);
    }

    //    Convert a String to a List of Prices
    public static List<Price> convertCsvToPrices(String csv) throws IllegalStateException, FileNotFoundException {
        List<Price> prices = new CsvToBeanBuilder(new StringReader(csv)).withType(Price.class).build().parse();

        return prices;

    }

}