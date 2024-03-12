package com.example.inditextest.domain.ports;

import com.example.inditextest.infrastructure.db.model.PriceEntity;

import java.util.List;

public interface PriceRepository {
    List<PriceEntity> findMatchingPricesForProductAndBrandIds(String productId, Integer brandId);
}
