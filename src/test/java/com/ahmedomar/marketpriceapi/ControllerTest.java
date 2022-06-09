package com.ahmedomar.marketpriceapi;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTest {
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;  //mockMvc is a mock object that is used to test the controller

    @Autowired
    private PriceRepository repository;  //repository is a mock object that is used to test the controller

    @Autowired
    private PriceController controller; //controller is a mock object that is used to test the controller

    @Autowired
    private PriceFeedReader priceFeedReader; //priceFeedReader is a mock object that is used to test the controller

    @Before
    public void setUp() {
        System.out.println("Initiating the controller test");

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        repository.findAllPrices().clear(); //clear the list of prices

        Price price = new Price();
        price.setBid(1.0999);
        price.setAsk(1.2011);
        price.setId(106L);
        price.setName("EUR/USD");
        repository.findAllPrices().add(price); //add the price to the list of prices

    }

    @Test
    public void checksLatestPriceAndReturns200() throws Exception {
        String response = mockMvc.perform(get("/latest/EUR/USD")) //perform the get request
                .andExpect(status().isOk()) //check that the response is 200
                .andReturn().getResponse().getContentAsString(); //get the response as a string
        assertEquals("{\"id\":106,\"name\":\"EUR/USD\",\"bid\":1.0999,\"ask\":1.2011}", response); //check that the response is correct
        System.out.println(response); //print the response
    }

}
