package com.example.inditextest.adapters.api;

import com.example.inditextest.domain.model.Price;
import com.example.inditextest.domain.ports.PricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Component
public class PricesFacade {
    private final PricesService pricesService;
    Price get(OffsetDateTime currentDateTime, String productId, Integer brandId) {
        return pricesService.get(currentDateTime, productId, brandId);
    }
}
