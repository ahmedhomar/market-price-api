package com.ahmedomar.marketpriceapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    PriceService service;

    @Autowired
    public PriceController(PriceService service) {
        this.service = service;
    }

//    GET requests

    //get the LATEST price from ALL currencies:
    @GetMapping("/price-latest") //http://localhost:8080/price-latest
    @ResponseBody //returns the response body as a string
    public ResponseEntity<Price> getLatestPrice() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getLatestPrice());

    }
//get all prices from all currencies:

    @GetMapping("/price-all") //http://localhost:8080/price-all
    @ResponseBody
    public ResponseEntity<List<Price>> getAllPrices() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllPrices());
    }

    //get a specific price:
    @GetMapping("/price/{id}") //http://localhost:8080/price/1
    @ResponseBody
    public ResponseEntity<Price> getPriceById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPriceById(id));
    }

    //get the lowest price of a currency:
    @GetMapping("/price-lowest/{name}") //http://localhost:8080/price-lowest/USD
    @ResponseBody
    public ResponseEntity<Price> getLowestPriceByName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findLowestPriceByName(name));
    }

    //get all prices of a currency:
    @GetMapping("/price-all/{name}") //http://localhost:8080/price-all/USD
    @ResponseBody
    public ResponseEntity<List<Price>> getAllPricesByName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllPricesByName(name));
    }


}
