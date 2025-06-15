package org.brokerage.repository;

import org.brokerage.dto.CustomerAssetDTO;
import org.brokerage.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    @Query("""
            select new org.brokerage.dto.CustomerAssetDTO(
                ca.asset.assetName,
                ca.totalSize,
                ca.usableSize,
                ca.asset.price
            )
            from CustomerAsset ca
            where ca.customer.id = :customerId
    """)
    List<CustomerAssetDTO> findAssetsByCustomerId(@Param("customerId") Long customerId);

    Optional<Asset> findByAssetName(String assetName);

}
