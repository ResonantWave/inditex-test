package com.example.inditextest.domain.ports;

import com.example.inditextest.infrastructure.db.model.PriceEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceRepository {
    List<PriceEntity> findMatchingPricesForProductAndBrandIdsAndDatetime(String productId, Integer brandId,
                                                                         OffsetDateTime currentDateTime);
}
