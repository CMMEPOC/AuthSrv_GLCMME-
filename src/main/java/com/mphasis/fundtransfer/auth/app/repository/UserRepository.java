package com.mphasis.fundtransfer.auth.app.repository;

import com.mphasis.fundtransfer.auth.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUserId(UUID userId);
    Optional<UserEntity> findByUsername(String username);
}
