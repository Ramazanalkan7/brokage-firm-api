package org.brokerage.repository;

import org.brokerage.model.CustomerAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAssetRepository extends JpaRepository<CustomerAsset, Long> {
    Optional<CustomerAsset> findByCustomer_IdAndAsset_AssetName(Long customerId, String assetName);
}
