package org.brokerage.service.impl;

import lombok.RequiredArgsConstructor;

import org.brokerage.dto.CustomerAssetDTO;
import org.brokerage.model.Asset;
import org.brokerage.repository.AssetRepository;
import org.brokerage.service.AssetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public List<Asset> listAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public List<CustomerAssetDTO> findAssetsByCustomer(Long customerId) {
        List<CustomerAssetDTO> assets = assetRepository.findAssetsByCustomerId(customerId);

        return assets.stream()
                .filter(asset -> asset.usableSize() != null && asset.usableSize().compareTo(BigDecimal.ZERO) > 0)
                .toList();
    }
}