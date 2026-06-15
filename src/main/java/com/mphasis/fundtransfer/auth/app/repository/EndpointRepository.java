package com.mphasis.fundtransfer.auth.app.repository;

import com.mphasis.fundtransfer.auth.app.entity.EndpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EndpointRepository extends JpaRepository<EndpointEntity, Integer> {
    Optional<EndpointEntity> findById(Integer id);
    EndpointEntity findByEndpointName(String endpointName);
}
