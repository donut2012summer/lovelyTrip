package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.SupplierDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.SupplierEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public class SupplierMapper extends BaseMapper{
    @Override
    BaseEntity mapToEntity(BaseDto baseDto) {

        // convert baseDto to supplierDto
        SupplierDto supplierDto = (SupplierDto) baseDto;
        // new a supplierEntity
        SupplierEntity supplierEntity = new SupplierEntity();
        // set data
        supplierEntity.setCompanyName(supplierDto.getCompanyName());
        supplierEntity.setLicenseNo(supplierDto.getLicenseNo());
        supplierEntity.setContactName(supplierDto.getContactName());
        supplierEntity.setEmail(supplierDto.getEmail());
        supplierEntity.setPhone(supplierDto.getPhone());
        supplierEntity.setStatus(supplierDto.getStatus());

        return supplierEntity;
    }

    @Override
    BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer) {
        // convert baseEntity to supplierEntity
        SupplierEntity supplierEntity = (SupplierEntity) baseEntity;
        // new a supplierDto
        SupplierDto supplierDto = new SupplierDto();
        // set data + time stamp + container

        supplierDto.setCompanyName(supplierEntity.getCompanyName());
        supplierDto.setLicenseNo(supplierEntity.getLicenseNo());
        supplierDto.setContactName(supplierEntity.getContactName());
        supplierDto.setEmail(supplierEntity.getEmail());
        supplierDto.setPhone(supplierEntity.getPhone());
        supplierDto.setStatus(supplierEntity.getStatus());

        supplierDto.setCreatedTime(supplierEntity.getCreatedTime());
        supplierDto.setUpdatedTime(supplierEntity.getUpdatedTime());
        supplierDto.setMessageInfoContainer(messageInfoContainer);
        return supplierDto;
    }
}
