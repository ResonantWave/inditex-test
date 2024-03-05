package com.example.inditextest.domain.model;

import com.example.inditextest.adapters.db.model.PricePojo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

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
    private final String startDate;
    @JsonProperty("endDate")
    private final String endDate;
    @JsonProperty("price")
    private final BigDecimal price;

    public static Price of(final PricePojo price) {
        return new Price(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate().toString(), //fixme
                price.getEndDate().toString(),
                price.getPrice());
    }

}