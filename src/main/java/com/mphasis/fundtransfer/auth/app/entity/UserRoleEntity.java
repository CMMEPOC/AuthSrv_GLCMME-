package com.mphasis.fundtransfer.auth.app.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    @EmbeddedId
    private UserRoleEmbedding userRoleEmbedding;
}
