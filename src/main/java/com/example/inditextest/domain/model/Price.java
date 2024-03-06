package com.example.inditextest.domain.model;

import com.example.inditextest.infrastructure.db.model.PriceDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class Price {
    @JsonProperty("productId")
    private final String productId;
    @JsonProperty("brandId")
    private final Integer brandId;
    @JsonProperty("priceList")
    private final String priceList;
    @JsonProperty("startDate")
    private final OffsetDateTime startDate;
    @JsonProperty("endDate")
    private final OffsetDateTime endDate;
    @JsonProperty("price")
    private final BigDecimal price;

    public static Price of(final PriceDTO price) {
        return new Price(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice());
    }

}