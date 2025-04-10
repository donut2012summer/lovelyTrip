package com.vic.lovelytrip.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto extends BaseDto{
    private String firstName;
    private String lastName;

    private String email;
    private String passwordHash;

    private String phone;
    private LocalDate dateOfBirth;
    private int userRole;
    private int status;
}
