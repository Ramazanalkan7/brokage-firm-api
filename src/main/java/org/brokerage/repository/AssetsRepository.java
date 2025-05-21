package org.brokerage.repository;

import org.brokerage.model.Assets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
    List<Assets> findByCustomerId(Long customerId);
}