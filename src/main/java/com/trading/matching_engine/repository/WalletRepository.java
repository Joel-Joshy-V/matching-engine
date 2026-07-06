package com.trading.matching_engine.repository;

import com.trading.matching_engine.entity.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    // This lock prevents any other database transaction from reading or writing to this wallet
    // until the current transaction is fully committed or rolled back.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT w FROM Wallet w WHERE w.user.id = :userId AND w.assetType = :assetType")
    Optional<Wallet> findByUserIdAndAssetTypeLocked(@Param("userId") Long userId, @Param("assetType") String assetType);
}