package com.mphasis.fundtransfer.auth.app.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_endpoint")
public class RoleEndpointEntity {
    @EmbeddedId
    private RoleEndpointEmbedding roleEndpointEmbedding;
}
