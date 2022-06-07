package com.ahmedomar.marketpriceapi;



import java.util.List;

public interface RepositoryInterface {

    public List<Price> findAllPrices();

    public Price getLatestPrice();

    public Price getPriceById(Long id);

    public Price findLowestPriceByName(String name);

    public List<Price> findAllPricesByName(String name);
}
