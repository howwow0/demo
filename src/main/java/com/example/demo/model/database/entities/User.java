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
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(unique = true, length = 35)
    private String username;

    @Column(nullable = false, length = 25)
    private String password;

    @Column(unique = true, nullable = false, length = 256)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

}
