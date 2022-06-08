package com.ahmedomar.marketpriceapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {
    @Autowired
    PriceService service;

    //get the LATEST price from ALL currencies:
    @GetMapping("/price-latest")
    @ResponseBody
    private Price getLatestPrice() {
        return service.getLatestPrice();
    }
//get all prices from all currencies:

    @GetMapping("/price-all")
    @ResponseBody
    private List<Price> getAllPrices() {
        return service.findAllPrices();
    }

    //get a specific price:
    @GetMapping("/price/{id}")
    @ResponseBody
    private Price getPriceById(Long id) {
        return service.getPriceById(id);
    }

    //get the lowest price of a currency:
    @GetMapping("/price-lowest/{name}")
    @ResponseBody
    private Price getLowestPriceByName(String name) {
        return service.findLowestPriceByName(name);
    }

    //get all prices of a currency:
    @GetMapping("/price-all/{name}")
    @ResponseBody
    private List<Price> getAllPricesByName(String name) {
        return service.findAllPricesByName(name);
    }


}
