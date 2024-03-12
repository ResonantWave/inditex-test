package com.example.inditextest.infrastructure.api.model.model;

import com.example.inditextest.domain.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PriceResponse {
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
    @JsonProperty("currency")
    private final String currency;

    public static PriceResponse of(final Price price) {
        return new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency());
    }

}