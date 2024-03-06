package com.example.inditextest.application.api;

import com.example.inditextest.application.model.PriceResponse;
import com.example.inditextest.domain.PricesService;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PricesEndpoint {

    private final PricesService pricesService;

    @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public PriceResponse getPrices(@RequestParam(value = "currentDateTime") OffsetDateTime currentDateTime,
                                   @RequestParam(value = "productId") @Size(max = 10) String productId,
                                   @RequestParam(value = "brandId") Integer brandId) {
        return PriceResponse.of(pricesService.get(currentDateTime, productId, brandId));
    }
}
