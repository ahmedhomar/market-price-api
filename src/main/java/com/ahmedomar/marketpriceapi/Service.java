package com.ahmedomar.marketpriceapi;



public class Service implements ServiceInterface {


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
