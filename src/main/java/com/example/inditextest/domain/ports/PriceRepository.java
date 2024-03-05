package com.example.inditextest.domain.ports;

import com.example.inditextest.adapters.db.model.PricePojo;

import java.util.List;

public interface PriceRepository {
    List<PricePojo> findMatchingPricesForProductAndBrandIds(String productId, Integer brandId);
}
