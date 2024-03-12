package com.example.inditextest.infrastructure.api;

import com.example.inditextest.infrastructure.api.model.PriceResponse;
import com.example.inditextest.application.PricesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class PricesEndpoint {

    private final PricesService pricesService;

    @Operation(
            summary = "Get a product price by time",
            description = "Get the price of a product (also specifying brand) in the given time."
    )
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PriceResponse.class), mediaType = APPLICATION_JSON_VALUE) })
    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    @GetMapping(value = "/price", produces = APPLICATION_JSON_VALUE)
    public PriceResponse getPrices(@RequestParam(value = "currentDateTime") OffsetDateTime currentDateTime,
                                   @RequestParam(value = "productId") @Size(max = 10) String productId,
                                   @RequestParam(value = "brandId") Integer brandId) {
        return PriceResponse.of(pricesService.get(currentDateTime, productId, brandId));
    }
}
