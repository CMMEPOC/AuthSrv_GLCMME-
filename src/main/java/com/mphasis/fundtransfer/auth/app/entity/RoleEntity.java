package com.mphasis.fundtransfer.auth.app.entity;

import com.mphasis.fundtransfer.auth.api.constants.AuthRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue
    @Column(name="role_id")
    private Integer roleId;

    @Column(name="role_name", nullable=false)
    private String roleName;
}
