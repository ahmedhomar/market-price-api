package com.ahmedomar.marketpriceapi;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ahmedomar.marketpriceapi.PriceRepository;
import com.ahmedomar.marketpriceapi.PriceFeedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc

public class PriceTest {

    @Autowired
    private PriceRepository repository;

    @Autowired
    private PriceFeedReader priceFeedReader;

    @Before
    public void setUp() {
        System.out.println("Setting up the test");
    }

    @Test
     public void checkPriceIsCorrectlyAdjusted(){
        String entry106 = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
        repository.findAllPrices().clear(); //clear the list of prices

        priceFeedReader.onMessage(entry106); //publish the price
        assertEquals(1.0999, repository.findAllPrices().get(0).getBid());
        assertEquals(1.2011, repository.findAllPrices().get(0).getAsk());

    }




}
