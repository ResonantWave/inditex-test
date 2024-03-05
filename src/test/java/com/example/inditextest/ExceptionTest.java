package com.example.inditextest;

import com.example.inditextest.adapters.db.PriceJpaRepository;
import com.example.inditextest.adapters.db.PriceRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@RequiredArgsConstructor
@AutoConfigureMockMvc
public class ExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    PriceRepository priceRepository;

    @Autowired
    PriceJpaRepository priceJpaRepository;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // TODO: PARAMETERISE TESTS
    @Test
    void exceptionsAreHandledCorrectly() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("currentDateTime", "INVALID")
                        .param("productId", "INVALID")
                        .param("brandId", "INVALID"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorReason").value("Unexpected parameter values"));
    }

    @Test
    void exceptionsAreHandledCorrectly2() throws Exception {
        when(priceRepository.findMatchingPricesForProductAndBrandIds(any(), any())).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/prices")
                        .param("currentDateTime", OffsetDateTime.now().toString())
                        .param("productId", "12345")
                        .param("brandId", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorReason").value("Unexpected parameter values"));
    }

    @Test
    void exceptionsAreHandledCorrectly3() throws Exception {
        priceJpaRepository.deleteAll();
        when(priceRepository.findMatchingPricesForProductAndBrandIds(any(), any())).thenCallRealMethod();

        mockMvc.perform(get("/prices")
                        .param("currentDateTime", "2020-06-14T10:00:00.000Z")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorReason").value("Unexpected parameter values"));
    }
}