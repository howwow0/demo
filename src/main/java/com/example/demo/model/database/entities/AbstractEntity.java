package com.example.demo.model.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id = 0;

    @Setter
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt = LocalDateTime.now();

    @Column()
    @UpdateTimestamp
    private LocalDate updatedAt;
}
