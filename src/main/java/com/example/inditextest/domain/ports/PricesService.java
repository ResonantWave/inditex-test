package com.example.inditextest.domain.ports;

import com.example.inditextest.adapters.db.model.PricePojo;
import com.example.inditextest.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricesService {

    private final PriceRepository priceRepository;

    public Price get(OffsetDateTime currentDateTime, String productId, Integer brandId) {
        List<PricePojo> matchingPricesForProductAndBrandIds = priceRepository.findMatchingPricesForProductAndBrandIds(productId, brandId);

        return matchingPricesForProductAndBrandIds.stream()
                .filter(x -> currentDateTime.isAfter(x.getStartDate()) && currentDateTime.isBefore(x.getEndDate()))
                .sorted(Comparator.comparing(PricePojo::getPrice).reversed())
                .max(Comparator.comparing(PricePojo::isPriority))
                .map(Price::of)
                .get();
    }
}
