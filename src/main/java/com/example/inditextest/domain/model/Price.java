package com.example.inditextest.domain.model;

import com.example.inditextest.infrastructure.db.model.PriceEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Price {
    private final String productId;
    private final Integer brandId;
    private final String priceList;
    private final OffsetDateTime startDate;
    private final OffsetDateTime endDate;
    private final BigDecimal price;
    private final String currency;

    public static Price of(final PriceEntity price) {
        return new Price(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency());
    }

}