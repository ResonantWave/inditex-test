package com.example.inditextest.adapters.db;

import com.example.inditextest.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PriceRepository implements com.example.inditextest.domain.ports.PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public List<Price> findMatchingPricesForProductAndBrandIds(String currentDateTime, String productId, Integer brandId) {
        return priceJpaRepository.findByBrandIdAndProductId(brandId, productId)
                .stream().map(Price::of)
                .collect(Collectors.toList());
    }
}
