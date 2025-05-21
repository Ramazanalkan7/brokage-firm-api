package org.brokerage.controller;

import lombok.RequiredArgsConstructor;

import org.brokerage.model.Assets;
import org.brokerage.service.AssetsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetsController {

    private final AssetsService assetService;

    @GetMapping
    public List<Assets> getAssets(@RequestParam Long customerId) {
        return assetService.listAssets(customerId);
    }
}