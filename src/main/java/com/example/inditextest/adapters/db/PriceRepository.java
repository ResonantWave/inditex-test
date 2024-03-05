package com.example.inditextest.adapters.db;

import com.example.inditextest.adapters.db.model.PricePojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceRepository implements com.example.inditextest.domain.ports.PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public List<PricePojo> findMatchingPricesForProductAndBrandIds(String productId, Integer brandId) {
        return priceJpaRepository.findByBrandIdAndProductId(brandId, productId);
    }
}
