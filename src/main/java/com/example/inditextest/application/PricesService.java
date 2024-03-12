package com.example.inditextest.application;

import com.example.inditextest.domain.exception.PriceNotFoundException;
import com.example.inditextest.domain.model.Price;
import com.example.inditextest.domain.ports.PriceRepository;
import com.example.inditextest.infrastructure.db.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricesService implements com.example.inditextest.domain.ports.PricesService {

    private final PriceRepository priceRepository;

    public Price get(OffsetDateTime currentDateTime, String productId, Integer brandId) {
        List<PriceEntity> matchingPricesForProductAndBrandIds =
                priceRepository.findMatchingPricesForProductAndBrandIdsAndDatetime(productId, brandId, currentDateTime);

        return matchingPricesForProductAndBrandIds.stream()
                .findFirst()
                .map(Price::of)
                .orElseThrow(() -> new PriceNotFoundException(currentDateTime, productId, brandId));
    }
}
