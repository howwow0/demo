package com.example.demo.model.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends AbstractEntity {
    @Column(nullable = false, length = 500, name = "value")
    private String value;

    @Column(nullable = false, length = 30, name = "owner_username")
    private String ownerUsername;
}
