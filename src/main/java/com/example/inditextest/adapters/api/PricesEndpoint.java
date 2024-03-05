package com.example.inditextest.adapters.api;

import com.example.inditextest.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PricesEndpoint {

    private final PricesFacade pricesFacade;

    @GetMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
    public Price prices(@RequestParam(value = "currentDateTime") OffsetDateTime currentDateTime,
                        @RequestParam(value = "productId") String productId,
                        @RequestParam(value = "brandId") Integer brandId) {
        return pricesFacade.get(currentDateTime, productId, brandId);
    }
}
