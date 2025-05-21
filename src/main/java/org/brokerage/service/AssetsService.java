package org.brokerage.service;

import org.brokerage.model.Assets;

import java.util.List;

public interface AssetsService {
    List<Assets> listAssets(Long customerId);
}