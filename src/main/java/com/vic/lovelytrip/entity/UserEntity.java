package com.vic.lovelytrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


import java.time.LocalDate;

@Entity
@Table(name = "user")
@Data
public class UserEntity extends BaseEntity{

    private String firstName;
    private String lastName;

    private String email;
    private String passwordHash;

    private String phone;
    private LocalDate dateOfBirth;
    private int userRole;
    private int status;

}
