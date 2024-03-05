package com.example.inditextest.domain.ports;

import com.example.inditextest.adapters.db.model.PricePojo;
import com.example.inditextest.domain.model.Price;

import java.util.List;

public interface PriceRepository {
    List<PricePojo> findMatchingPricesForProductAndBrandIds(String productId, Integer brandId);
}
