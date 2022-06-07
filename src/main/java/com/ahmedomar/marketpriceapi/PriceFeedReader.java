package com.ahmedomar.marketpriceapi;




public class PriceFeedReader {

    private Repository repository;
    private Service service;

    //Constructor
    public PriceFeedReader(Repository repository, Service service) {
        this.repository = repository;
        this.service = service;
    }
    public void onMessage(String message) {
        try {
            Price price = CsvFeedHandler.convertCsvToPrice(message);
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


