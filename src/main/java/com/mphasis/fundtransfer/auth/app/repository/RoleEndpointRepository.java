package com.mphasis.fundtransfer.auth.app.repository;

import com.mphasis.fundtransfer.auth.app.entity.RoleEndpointEmbedding;
import com.mphasis.fundtransfer.auth.app.entity.RoleEndpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleEndpointRepository extends JpaRepository<RoleEndpointEntity, RoleEndpointEmbedding> {
    Optional<RoleEndpointEntity> findById(RoleEndpointEmbedding roleEndpointEmbedding);
}
