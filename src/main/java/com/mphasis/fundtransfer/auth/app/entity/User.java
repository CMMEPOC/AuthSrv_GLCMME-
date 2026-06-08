package com.mphasis.fundtransfer.auth.app.entity;

import com.mphasis.fundtransfer.auth.api.AuthAccountStatus;
import com.mphasis.fundtransfer.auth.api.AuthRole;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "auth_users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AuthAccountStatus accountStatus = AuthAccountStatus.Active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private AuthRole role = AuthRole.CUSTOMER;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public User() {
    }

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public AuthAccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AuthAccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AuthRole getRole() {
        return role;
    }

    public void setRole(AuthRole role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
