package org.brokerage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_assets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customer_id", "asset_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "total_size", precision = 18, scale = 6, nullable = false)
    private BigDecimal totalSize;

    @Column(name = "usable_size", precision = 18, scale = 6, nullable = false)
    private BigDecimal usableSize;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdated = LocalDateTime.now();
    }

}
