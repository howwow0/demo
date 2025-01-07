package com.example.demo.model.database.repository;

import com.example.demo.model.database.entities.RefreshToken;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CommonRepository<RefreshToken> {
    RefreshToken findByOwnerUsername(String ownerUsername);

    RefreshToken findByValue(String value);
}
