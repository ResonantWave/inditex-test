package com.example.inditextest;

import com.example.inditextest.adapters.db.PriceRepositoryImpl;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@RequiredArgsConstructor
@AutoConfigureMockMvc
class ExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    PriceRepositoryImpl priceRepositoryImpl;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @ParameterizedTest
    @MethodSource
    void exceptionsAreHandledCorrectly(String currentDateTime, String productId, String brandId, ResultMatcher expectedStatus
                                        ) throws Exception {
        when(priceRepositoryImpl.findMatchingPricesForProductAndBrandIds(any(), any())).thenCallRealMethod();

        mockMvc.perform(get("/price")
                        .param("currentDateTime", currentDateTime)
                        .param("productId", productId)
                        .param("brandId", brandId))
                .andExpect(expectedStatus);
    }

    static Stream<Arguments> exceptionsAreHandledCorrectly() {
        return Stream.of(
                Arguments.of("INVALID", "INVALID", "INVALID", status().isBadRequest(), null),
                Arguments.of("2020-06-14T10:00:00.000Z", "35455", "2", status().isNotFound())
        );
    }

    @Test
    void exceptionsAreHandledCorrectly2() throws Exception {
        Mockito.doThrow(new PersistenceException()).when(priceRepositoryImpl).findMatchingPricesForProductAndBrandIds(any(), any());
        mockMvc.perform(get("/price")
                        .param("currentDateTime", OffsetDateTime.now().toString())
                        .param("productId", "12345")
                        .param("brandId", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorReason").value("Unexpected parameter values"));
    }
}