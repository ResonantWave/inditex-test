package com.example.inditextest.adapters.db;

import com.example.inditextest.adapters.db.model.PricePojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PricePojo, Integer> {
    List<PricePojo> findByBrandIdAndProductId(Integer brandId, String productId);
}
