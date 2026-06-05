package com.mphasis.fundtransfer.authentication.repository;

import com.mphasis.fundtransfer.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLoginId(String loginId);
}
