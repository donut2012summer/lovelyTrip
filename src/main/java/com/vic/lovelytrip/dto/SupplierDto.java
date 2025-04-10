package com.vic.lovelytrip.dto;

import lombok.Data;

@Data
public class SupplierDto extends BaseDto{
    private String companyName;
    private String licenseNo;
    private String contactName;
    private String email;
    private String phone;
    private int status;
}
