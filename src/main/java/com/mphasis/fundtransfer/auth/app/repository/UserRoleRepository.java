package com.mphasis.fundtransfer.auth.app.repository;

import com.mphasis.fundtransfer.auth.app.entity.UserRoleEmbedding;
import com.mphasis.fundtransfer.auth.app.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleEmbedding> {
    UserRoleEntity findByUserRoleEmbedding_UserId(UUID userId);
}
