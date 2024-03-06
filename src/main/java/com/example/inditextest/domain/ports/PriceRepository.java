package com.example.inditextest.domain.ports;

import com.example.inditextest.infrastructure.db.model.PriceDTO;

import java.util.List;

public interface PriceRepository {
    List<PriceDTO> findMatchingPricesForProductAndBrandIds(String productId, Integer brandId);
}
