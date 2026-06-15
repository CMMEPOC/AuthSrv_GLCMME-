package com.mphasis.fundtransfer.auth.app.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEndpointEmbedding implements Serializable {
    private Integer roleId;
    private Integer endpointId;
}