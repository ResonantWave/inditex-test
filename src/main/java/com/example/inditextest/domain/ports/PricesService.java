package com.example.inditextest.domain.ports;

import com.example.inditextest.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricesService {

    private final PriceRepository priceRepository;

    public Price get(String currentDateTime, String productId, Integer brandId) {
        return priceRepository.find(currentDateTime, productId, brandId);
    }
}
