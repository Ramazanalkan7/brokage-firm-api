package org.brokerage.service.impl;

import lombok.RequiredArgsConstructor;

import org.brokerage.model.Assets;
import org.brokerage.repository.AssetsRepository;
import org.brokerage.service.AssetsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetsServiceImpl implements AssetsService {

    private final AssetsRepository assetRepository;

    @Override
    public List<Assets> listAssets(Long customerId) {
        return assetRepository.findByCustomerId(customerId);
    }
}