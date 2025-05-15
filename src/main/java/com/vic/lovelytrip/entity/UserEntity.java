package com.vic.lovelytrip.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;


import java.time.LocalDate;

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
