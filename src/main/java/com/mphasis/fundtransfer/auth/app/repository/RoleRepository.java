package com.mphasis.fundtransfer.auth.app.repository;

import com.mphasis.fundtransfer.auth.app.entity.RoleEntity;
import com.mphasis.fundtransfer.auth.app.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRoleId(Integer roleId);
}
