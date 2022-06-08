package com.ahmedomar.marketpriceapi;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PriceService {


    public Price applyPriceMechanics(Price price) {
        price = adjustBidPX(price);
        price = adjustAskPX(price);

        return price;
    }

    private static Price adjustBidPX(Price price) {
        double valueSubtract = (price.getBid() * 0.001);
        price.setBid(price.getBid() - valueSubtract);

        return price;
    }

    private static Price adjustAskPX(Price price) {
        double valueAdd = (price.getAsk() * 0.001);
        price.setAsk(price.getAsk() + valueAdd);

        return price;
    }

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
