package com.example.inditextest.domain.ports;

import com.example.inditextest.domain.model.Price;

import java.time.OffsetDateTime;

public interface PriceService {
    Price get(OffsetDateTime currentDateTime, String productId, Integer brandId);
}
