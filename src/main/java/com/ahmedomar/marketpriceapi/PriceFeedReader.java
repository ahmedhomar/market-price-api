package com.ahmedomar.marketpriceapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ahmedomar.marketpriceapi.CsvFeedHandler;

import java.io.FileNotFoundException;

@Component
public class PriceFeedReader {

    @Autowired
    private PriceRepository repository;
    @Autowired
    private PriceService service;

    //Constructor
    public PriceFeedReader(PriceRepository repository, PriceService service) {
        this.repository = repository;
        this.service = service;
    }
    public void onMessage(String message) {
        try {
            Price price = CsvFeedHandler.getPriceFromCsv(message);
            price = service.applyPriceMechanics(price);
            publishPrice(price);
        } catch (IllegalStateException e) {
            System.out.println("Error in parsing message");
        }
    }

    private void publishPrice(Price price) {
        repository.findAllPrices().add(price);
    }

}


