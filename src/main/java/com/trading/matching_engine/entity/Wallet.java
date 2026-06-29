package com.trading.matching_engine.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "asset_type", nullable = false)
    private String assetType; // e.g., "USD", "BTC"

    // Crucial Rule: Financial Precision
    @Column(nullable = false, precision = 32, scale = 16)
    private BigDecimal balance;
}
