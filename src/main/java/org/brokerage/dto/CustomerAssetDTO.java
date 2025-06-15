package org.brokerage.dto;

import java.math.BigDecimal;

public record CustomerAssetDTO(
        String assetName,
        BigDecimal totalSize,
        BigDecimal usableSize,
        BigDecimal price) {

}
