package com.example.inditextest.domain.exception;

import java.time.OffsetDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(OffsetDateTime currentDateTime, String productId, Integer brandId) {
        super("Couldn't find any price for product %s from brand %s at %s".formatted(productId, brandId, currentDateTime));
    }
}
