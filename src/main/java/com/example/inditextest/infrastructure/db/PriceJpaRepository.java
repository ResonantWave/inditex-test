package com.example.inditextest.infrastructure.db;

import com.example.inditextest.infrastructure.db.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {
    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateIsGreaterThanEqualOrderByPriorityDesc(
            Integer brandId, String productId, OffsetDateTime startDateTime, OffsetDateTime endDateTime);
}