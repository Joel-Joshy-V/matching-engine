package com.trading.matching_engine.dto; // Use your exact package name

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class OrderRequestTest {

    // Initialize our validator inspector
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void shouldFailWhenPriceIsNegative() {
        // Step 1: Create an order with an invalid negative price
        OrderRequest request = new OrderRequest(
                1L,
                "BTC_USD",
                new BigDecimal("-5.00"), // INVALID: Below 0.00000001!
                new BigDecimal("1.0"),
                OrderSide.BUY
        );

        // Step 2: Run validation rules against the request
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(request);

        // Step 3: Verify that the violations list is NOT empty
        assertFalse(violations.isEmpty());
    }
}