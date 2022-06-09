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
    private PriceRepository repository; //repository is a mock object that is used to test the controller

    @Autowired
    private PriceFeedReader priceFeedReader; //priceFeedReader is a mock object that is used to test the controller

    @Before
    public void setUp() {
        System.out.println("Setting up the test");
    }



    @Test
    public void checkPricesProcessedCorrectlyWithNoneMissing() {
        String entry1 = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
        String entry2 = "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002";
        String entry3 = "108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002";
        String entry4 = "109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100";
        String entry5 = "110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110";

        priceFeedReader.onMessage(entry1);
        assertEquals(1, repository.findAllPrices().size()); //check that only one price is in the list

        priceFeedReader.onMessage(entry2);
        assertEquals(2, repository.findAllPrices().size()); //check that two prices are in the list

        priceFeedReader.onMessage(entry3);
        assertEquals(3, repository.findAllPrices().size()); //check that three prices are in the list

        priceFeedReader.onMessage(entry4);
        assertEquals(4, repository.findAllPrices().size()); //check that four prices are in the list
        priceFeedReader.onMessage(entry5);
        assertEquals(5, repository.findAllPrices().size());
    }

    @Test
     public void checkPriceIsCorrectlyAdjusted(){
        String entry106 = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
        repository.findAllPrices().clear(); //clear the list of prices

        priceFeedReader.onMessage(entry106); //publish the price
        assertEquals(1.0999, repository.findAllPrices().get(0).getBid()); //check that the bid is correct
        assertEquals(1.2011, repository.findAllPrices().get(0).getAsk()); //check that the ask is correct

    }




}
