package com.ahmedomar.marketpriceapi;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {
    PriceRepository repository;

    public PriceController(PriceRepository repository) {
        this.repository = repository;
    }

}
