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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@RequiredArgsConstructor
@AutoConfigureMockMvc
class BusinessTests {
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

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
	void pricesAreCorrect(LocalDateTime testTime, String productId, String brandId,
						  String expectedProductId, String expectedBrandId, String expectedPriceList,
						  LocalDateTime expectedStartDate, LocalDateTime expectedEndDate, BigDecimal expectedPrice) throws Exception {
		mockMvc.perform(get("/prices")
				.param("currentDateTime", testTime.toString())
				.param("productId", productId)
				.param("brandId", brandId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("productId").value(expectedProductId))
				.andExpect(jsonPath("brandId").value(expectedBrandId))
				.andExpect(jsonPath("priceList").value(expectedPriceList))
				.andExpect(jsonPath("startDate").value(expectedStartDate))
				.andExpect(jsonPath("endDate").value(expectedEndDate))
				.andExpect(jsonPath("price").value(expectedPrice));
	}

	static Stream<Arguments> pricesAreCorrect() {
		return Stream.of(
				Arguments.of(parse("2020-06-14-10.00.00"), "35455", "1",
						"35455", "1", "1", parse("2020-06-14-00.00.00"), parse("2020-12-31-23.59.59"), "35.50"),
				Arguments.of(parse("2020-06-14-16.00.00"), "35455", "1",
						"35455", "1", "2", parse("2020-06-14-15.00.00"), parse("2020-06-14-18.30.00"), "25.45"),
				Arguments.of(parse("2020-06-14-21.00.00"), "35455", "1",
						"35455", "1", "1", parse("2020-06-14-00.00.00"), parse("2020-12-31-23.59.59"), "35.50"),
				Arguments.of(parse("2020-06-15-10.00.00"), "35455", "1",
						"35455", "1", "3", parse("2020-06-15-00.00.00"), parse("2020-06-15-11.00.00"), "30.50"),
				Arguments.of(parse("2020-06-16-21.00.00"), "35455", "1",
						"35455", "1", "4", parse("2020-06-15-16.00.00"), parse("2020-12-31-23.59.59"), "38.95")
		);
	}

	private static LocalDateTime parse(String dateTime) {
		return LocalDateTime.parse(dateTime, dateTimeFormatter);
	}
}