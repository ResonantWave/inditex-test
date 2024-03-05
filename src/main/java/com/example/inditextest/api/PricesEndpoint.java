package com.example.inditextest.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PricesEndpoint {

    @GetMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean prices(@RequestParam(value = "currentDateTime") String currentDateTime,
                          @RequestParam(value = "productId") String productId,
                          @RequestParam(value = "brandId") String brandId) {
        return true;
    }
}
