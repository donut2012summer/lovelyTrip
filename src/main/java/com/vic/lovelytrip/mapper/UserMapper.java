package com.vic.lovelytrip.mapper;

import com.vic.lovelytrip.dto.BaseDto;
import com.vic.lovelytrip.dto.UserDto;
import com.vic.lovelytrip.entity.BaseEntity;
import com.vic.lovelytrip.entity.UserEntity;
import com.vic.lovelytrip.lib.MessageInfoContainer;

public class UserMapper extends BaseMapper{

    @Override
    BaseEntity mapToEntity(BaseDto baseDto) {

        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPasswordHash(userDto.getPasswordHash());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setDateOfBirth(userDto.getDateOfBirth());
        userEntity.setUserRole(userDto.getUserRole());
        userEntity.setStatus(userDto.getStatus());

        return userEntity;
    }

    @Override
    BaseDto mapToDto(BaseEntity baseEntity, MessageInfoContainer messageInfoContainer) {

        UserEntity userEntity = (UserEntity) baseEntity;
        UserDto userDto = new UserDto();

        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPasswordHash(userEntity.getPasswordHash());
        userDto.setPhone(userEntity.getPhone());
        userDto.setDateOfBirth(userEntity.getDateOfBirth());
        userDto.setUserRole(userEntity.getUserRole());
        userDto.setStatus(userEntity.getStatus());

        userDto.setId(userEntity.getId());
        userDto.setCreatedTime(userEntity.getCreatedTime());
        userDto.setUpdatedTime(userEntity.getUpdatedTime());
        userDto.setMessageInfoContainer(messageInfoContainer);

        return userDto;
    }
}
