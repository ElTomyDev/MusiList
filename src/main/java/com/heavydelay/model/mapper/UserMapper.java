package com.heavydelay.model.mapper;

import org.mapstruct.Mapper;

import com.heavydelay.model.dto.user.PrivateUserDto;
import com.heavydelay.model.dto.user.PublicUserDto;
import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    PublicUserDto toPublicDto(User user);
    PrivateUserDto toPrivateDto(User user);
    User toRegisterEntity(RegisterUserDto registerUserDto);
    User toPublicEntity(PublicUserDto userDto);
}
