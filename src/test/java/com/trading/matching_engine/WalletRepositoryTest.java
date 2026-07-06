package com.trading.matching_engine;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.TimeZone;

import com.trading.matching_engine.entity.User;
import com.trading.matching_engine.entity.Wallet;
import com.trading.matching_engine.repository.UserRepository;
import com.trading.matching_engine.repository.WalletRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Forces the test to use your real PostgreSQL container
class WalletRepositoryTest {

    // ---> THIS IS THE BLOCK YOU MISSED <---
    @BeforeAll
    static void setupTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
    }
    // --------------------------------------

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenFindByUserIdAndAssetTypeLocked_thenSuccess() {
        // 1. Set up test data
        User testUser = User.builder()
                .username("trader_johnDoe")
                .email("johnDoe@example.com")
                .build();
        testUser = userRepository.save(testUser);

        Wallet testWallet = Wallet.builder()
                .user(testUser)
                .assetType("USD")
                .balance(new BigDecimal("1000.0000000000000000"))
                .build();
        walletRepository.save(testWallet);

        // 2. Execute the locked query
        Optional<Wallet> lockedWallet = walletRepository.findByUserIdAndAssetTypeLocked(testUser.getId(), "USD");

        // 3. Verify it worked
        assertTrue(lockedWallet.isPresent());
        System.out.println("Successfully retrieved locked wallet for balance updates!");
    }
}