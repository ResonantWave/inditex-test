package com.example.inditextest.infrastructure.db;

import com.example.inditextest.infrastructure.db.model.PriceDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceDTO, Integer> {
    List<PriceDTO> findByBrandIdAndProductId(Integer brandId, String productId);
}
