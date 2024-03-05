package com.example.inditextest.adapters.api.model;

import com.example.inditextest.domain.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PricesResponse {
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

    public static PricesResponse of(final Price price) {
        return new PricesResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice());
    }
}
