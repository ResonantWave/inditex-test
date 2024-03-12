package com.example.inditextest.domain;

import com.example.inditextest.domain.exception.PriceNotFoundException;
import com.example.inditextest.domain.model.Price;
import com.example.inditextest.domain.ports.PriceRepository;
import com.example.inditextest.infrastructure.db.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class PricesService {

    private final PriceRepository priceRepository;

    public Price get(OffsetDateTime currentDateTime, String productId, Integer brandId) {
        List<PriceEntity> matchingPricesForProductAndBrandIds = priceRepository.findMatchingPricesForProductAndBrandIds(productId, brandId);

        return matchingPricesForProductAndBrandIds.stream()
                .filter(withinTimeRange(currentDateTime))
                .max(byHighestPriority())
                .map(Price::of)
                .orElseThrow(() -> new PriceNotFoundException(currentDateTime, productId, brandId));
    }

    private Comparator<PriceEntity> byHighestPriority() {
        return Comparator.comparing(PriceEntity::isPriority);
    }

    private Predicate<PriceEntity> withinTimeRange(OffsetDateTime currentDateTime) {
        return x -> currentDateTime.isAfter(x.getStartDate()) && currentDateTime.isBefore(x.getEndDate());
    }
}
