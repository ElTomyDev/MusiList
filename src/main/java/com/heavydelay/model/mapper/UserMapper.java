package com.heavydelay.model.mapper;

import org.mapstruct.Mapper;

import com.heavydelay.model.dto.user.PublicUserDto;
import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    PublicUserDto toDto(User user);
    User toEntity(RegisterUserDto registerUserDto);
    User toEntity(PublicUserDto userDto);
}
