package com.ahmedomar.marketpriceapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class PriceService {

    PriceRepository repository;

    @Autowired
    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }



    public List<Price> findAllPrices() {
        return repository.allPrices;
    }

    //get the latest price from all currencies
    public Price getLatestPrice() {

        return !repository.allPrices.isEmpty() ? repository.allPrices.get(repository.allPrices.size() - 1) : null; // if allPrices is empty, return null

    }

    // get a specific price
    public Price getPriceById(Long id) {
        return repository.allPrices.stream().filter(price -> price.getId().equals(id)).findFirst().orElseThrow(NoSuchElementException::new);
    }


    public Price findLowestPriceByName(String name) {
        List<Price> prices = repository.allPrices.stream().filter(price -> price.getName().equals(name))
                .collect(Collectors.toList());

        return prices.stream().min(Comparator.comparing(Price::getBid)).get();
    }


    public List<Price> findAllPricesByName(String name) {
        return repository.allPrices.stream().filter(price -> price.getName().equals(name)).collect(Collectors.toList());
    }



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




}
