package com.example.demo.model.dto.response.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public abstract class EntityResponse {
    protected Integer id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
