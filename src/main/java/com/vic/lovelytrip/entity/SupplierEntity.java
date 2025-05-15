package com.vic.lovelytrip.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;


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
