package org.brokerage.controller;

import lombok.RequiredArgsConstructor;
import org.brokerage.dto.CustomerAssetDTO;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.brokerage.model.Asset;
import org.brokerage.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.listAllAssets();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerAssetDTO>> getAssetsByCustomer(@PathVariable Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid customerId");
        }
        List<CustomerAssetDTO> assets = assetService.findAssetsByCustomer(customerId);
        return ResponseEntity.ok(assets);
    }
}