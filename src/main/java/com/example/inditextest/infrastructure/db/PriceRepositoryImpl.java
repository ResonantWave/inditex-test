package com.example.inditextest.infrastructure.db;

import com.example.inditextest.infrastructure.db.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceRepositoryImpl implements com.example.inditextest.domain.ports.PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public List<PriceEntity> findMatchingPricesForProductAndBrandIds(String productId, Integer brandId) {
        return priceJpaRepository.findByBrandIdAndProductId(brandId, productId);
    }
}
