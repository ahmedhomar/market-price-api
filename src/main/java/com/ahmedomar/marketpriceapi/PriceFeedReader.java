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
        try {  //try to parse the message
            Price price = CsvFeedHandler.getPriceFromCsv(message);  //get the price from the message
            price = service.applyPriceMechanics(price); //apply price mechanics
            publishPrice(price); //publish the price
        } catch (IllegalStateException e) { //if the message is not in the correct format, ignore it
            System.out.println("Error in parsing message");  //print error message
        }
    }

    /**
     * Publish the price to the repository
     * Ideally, the prices would be published to a database, but for the sake of simplicity, I will publish them to the repository
     **/

    private void publishPrice(Price price) {
        repository.findAllPrices().add(price);
    }

}


