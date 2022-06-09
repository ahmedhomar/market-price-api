package com.ahmedomar.marketpriceapi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    //Store prices in this List containing all prices until the database is implemented
    static List<Price> allPrices = new ArrayList<>();

    public List<Price> findAllPrices();  //return all prices

    public Price getLatestPrice(); //return the latest price

    public Price getPriceById(Long id); //return a specific price

    public Price findLowestPriceByName(String name); //return the lowest price of a currency

    public List<Price> findAllPricesByName(String name); //return all prices of a currency


}

