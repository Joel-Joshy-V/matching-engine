package com.trading.matching_engine.dtowe ; // This tells Java where the file lives

import com.trading.matching_engine.dto.OrderSide;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequest(
        @NotNull Long userId,
        @NotNull String assetPair,
        @NotNull @DecimalMin(value = "0.00000001") BigDecimal price,
        @NotNull @DecimalMin(value = "0.00000001") BigDecimal quantity,
        @NotNull OrderSide side
) {}