package com.mphasis.fundtransfer.auth.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "endpoint")
public class EndpointEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="endpoint_name", nullable = false)
    private String endpointName;
}
