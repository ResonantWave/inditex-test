package com.example.inditextest;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@RequiredArgsConstructor
@AutoConfigureMockMvc
class PricesIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@ParameterizedTest
	@MethodSource
	void getsCorrectPriceForProduct(String testTime, String productId, String brandId,
									String expectedProductId, String expectedBrandId, String expectedPriceList,
									OffsetDateTime expectedStartDate, OffsetDateTime expectedEndDate, BigDecimal expectedPrice) throws Exception {
		mockMvc.perform(get("/prices")
				.param("currentDateTime", testTime)
				.param("productId", productId)
				.param("brandId", brandId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.productId").value(expectedProductId))
				.andExpect(jsonPath("$.brandId").value(expectedBrandId))
				.andExpect(jsonPath("$.priceList").value(expectedPriceList))
				.andExpect(jsonPath("$.startDate").value(ISO_OFFSET_DATE_TIME.format(expectedStartDate)))
				.andExpect(jsonPath("$.endDate").value(ISO_OFFSET_DATE_TIME.format(expectedEndDate)))
				.andExpect(jsonPath("$.price").value(expectedPrice.doubleValue()));
	}

	static Stream<Arguments> getsCorrectPriceForProduct() {
		return Stream.of(
				Arguments.of("2020-06-14T10:00:00.000Z", "35455", "1",
						"35455", "1", "1", "2020-06-14T00:00:00.000Z", "2020-12-31T23:59:59.000Z", "35.50"),
				Arguments.of("2020-06-14T16:00:00.000Z", "35455", "1",
						"35455", "1", "2", "2020-06-14T15:00:00.000Z", "2020-06-14T18:30:00.000Z", "25.45"),
				Arguments.of("2020-06-14T21:00:00.000Z", "35455", "1",
						"35455", "1", "1", "2020-06-14T00:00:00.000Z", "2020-12-31T23:59:59.000Z", "35.50"),
				Arguments.of("2020-06-15T10:00:00.000Z", "35455", "1",
						"35455", "1", "3", "2020-06-15T00:00:00.000Z", "2020-06-15T11:00:00.000Z", "30.50"),
				Arguments.of("2020-06-16T21:00:00.000Z", "35455", "1",
						"35455", "1", "4", "2020-06-15T16:00:00.000Z", "2020-12-31T23:59:59.000Z", "38.95")
		);
	}
}