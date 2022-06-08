package com.ahmedomar.marketpriceapi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface PriceRepository extends JpaRepository<Price, Integer> {

    public List<Price> findAllPrices();

    public Price getLatestPrice();

    public Price getPriceById(Long id);

    public Price findLowestPriceByName(String name);

    public List<Price> findAllPricesByName(String name);


}

