package org.brokerage.service;

import org.brokerage.dto.AssetDto;
import org.brokerage.dto.CustomerAssetDTO;
import org.brokerage.model.Asset;

import java.util.List;

public interface AssetService {
    List<Asset> listAllAssets();
    List<CustomerAssetDTO> findAssetsByCustomer(Long customerId);
}