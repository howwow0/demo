package com.example.demo.model.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Setter
    @Column(nullable = false, updatable = false, name = "`created_at`")
    @CurrentTimestamp
    protected LocalDateTime createdAt;

    @Column(name = "`updated_at`")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
