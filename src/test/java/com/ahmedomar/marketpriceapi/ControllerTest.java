package com.ahmedomar.marketpriceapi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
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
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository repository;

    @Autowired
    private PriceController controller;

    @Autowired
    private PriceFeedReader priceFeedReader;

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
        repository.findAllPrices().add(price);

    }

    @Test
    public void checksLatestPriceAndReturns200() throws Exception {
        String response = mockMvc.perform(get("/latest/EUR/USD"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("{\"id\":106,\"name\":\"EUR/USD\",\"bid\":1.0999,\"ask\":1.2011}", response);
        System.out.println(response);
    }

}
