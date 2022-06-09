package com.ahmedomar.marketpriceapi;


import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;



@Service
public class PriceService {

    public List<Price> findAllPrices() {
        return PriceRepository.allPrices;
    }

    //get the latest price from all currencies
    public Price getLatestPrice() {

        return !PriceRepository.allPrices.isEmpty() ? PriceRepository //if the list is not empty
                .allPrices //get all prices
                .get(PriceRepository.allPrices.size() - 1) : null; // if allPrices is empty, return null

    }

    // get a specific price
    public Price getPriceById(Long id) {
        return PriceRepository.allPrices.stream() //get all prices
                .filter(price -> price.getId().equals(id)).findFirst() //filter by id
                .orElseThrow(NoSuchElementException::new); //if not found, throw exception
    }


    public Price findLowestPriceByName(String name) { //get the lowest price of a currency
        List<Price> prices = PriceRepository.allPrices.stream() //get all prices
                .filter(price -> price.getName().equals(name)).toList(); //collect to list

        return prices.stream().min(Comparator.comparing(Price::getBid)).get(); //get the lowest price
    }


    public List<Price> findAllPricesByName(String name) { //get all prices of a currency instrument
        return PriceRepository.allPrices.stream() //get all prices
                .filter(price -> price.getName().equals(name)).toList();
                //collect to list
    }

    /*
    *  Apply all the price mechanics to the price to be adjusted
    * return the adjusted price
    *
    * */

    public Price applyPriceMechanics(Price price) { //apply price mechanics to a price
        price = adjustBidPX(price); //adjust bid price
        price = adjustAskPX(price); //adjust ask price

        return price;
    }


    private static Price adjustBidPX(Price price) {
        double valueSubtract = (price.getBid() * 0.001); //subtract 0.001% from bid price
        price.setBid(price.getBid() - valueSubtract); //set bid price

        return price;
    }

    private static Price adjustAskPX(Price price) {
        double valueAdd = (price.getAsk() * 0.001); //add 0.001% to ask price
        price.setAsk(price.getAsk() + valueAdd); //set ask price

        return price;
    }


}
