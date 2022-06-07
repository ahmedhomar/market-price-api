package com.ahmedomar.marketpriceapi;



import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Repository implements RepositoryInterface {

    //Store prices in this List containing all prices
    private static List<Price> allPrices = new ArrayList<>();

    public List<Price> findAllPrices() {
        return allPrices;
    }

    //get the latest price from all currencies
    public Price getLatestPrice() {

        return !allPrices.isEmpty() ? allPrices.get(allPrices.size() - 1) : null; // if allPrices is empty, return null

    }

    // get a specific price
    public Price getPriceById(Long id) {
        return allPrices.stream().filter(price -> price.getId().equals(id)).findFirst().orElseThrow(NoSuchElementException::new);
    }


    public Price findLowestPriceByName(String name) {
        return null;
    }


    public List<Price> findAllPricesByName(String name) {
        return null;
    }


}

