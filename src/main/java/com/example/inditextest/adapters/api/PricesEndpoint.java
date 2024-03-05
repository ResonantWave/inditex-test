package com.example.inditextest.adapters.api;

import com.example.inditextest.domain.model.Price;
import com.example.inditextest.domain.ports.PricesService;
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

    @GetMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
    public Price getPrices(@RequestParam(value = "currentDateTime") OffsetDateTime currentDateTime,
                        @RequestParam(value = "productId") @Size(max = 10) String productId,
                        @RequestParam(value = "brandId") Integer brandId) {
        return pricesService.get(currentDateTime, productId, brandId);
    }
}
