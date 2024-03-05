package com.example.inditextest.adapters.api;

import com.example.inditextest.adapters.api.model.PricesResponse;
import com.example.inditextest.domain.model.Price;
import com.example.inditextest.domain.ports.PricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PricesFacade {
    private final PricesService pricesService;
    PricesResponse get(String currentDateTime, String productId, Integer brandId) {
        final Price price = pricesService.get(currentDateTime, productId, brandId);
        return PricesResponse.of(price);
    }
}
