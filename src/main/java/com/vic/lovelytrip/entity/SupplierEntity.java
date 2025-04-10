package com.vic.lovelytrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "supplier")
@Data
public class SupplierEntity extends BaseEntity{

    private String companyName;
    private String licenseNo;
    private String contactName;
    private String email;
    private String phone;
    private int status;
}
