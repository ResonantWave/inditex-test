package com.example.inditextest;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
class BusinessTests {
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

	@ParameterizedTest
	@MethodSource
	void pricesAreCorrect(LocalDateTime testTime, String productId, String brandId,
						  String expectedProductId, String expectedBrandId, String expectedPriceList,
						  LocalDateTime expectedStartDate, LocalDateTime expectedEndDate, BigDecimal expectedPrice) {

	}

	static Stream<Arguments> pricesAreCorrect() {
		return Stream.of(
				Arguments.of(parse("2020-06-14-10.00.00"), "35455", "1",
						"35455", "1", "1", parse("2020-06-14-00.00.00"), parse("2020-12-31-23.59.59"), "35.50"),
				Arguments.of(parse("2020-06-14-16.00.00)"), "35455", "1",
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