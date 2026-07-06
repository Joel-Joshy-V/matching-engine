package com.trading.matching_engine.repository;

import com.trading.matching_engine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring automatically knows how to generate the SQL for this based on the method name!
    boolean existsByUsername(String username);
}