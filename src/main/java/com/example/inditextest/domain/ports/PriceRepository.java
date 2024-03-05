package com.example.inditextest.domain.ports;

import com.example.inditextest.domain.model.Price;

import java.util.List;

public interface PriceRepository {
    List<Price> findMatchingPricesForProductAndBrandIds(String currentDateTime, String productId, Integer brandId);
}
