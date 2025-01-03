package com.example.demo.model.dto.response.entity;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
public abstract class EntityResponse {
    protected Integer id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
