package com.heavydelay.model.mapper;

import org.mapstruct.Mapper;

import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.dto.user.UserDto;
import com.heavydelay.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserDto registerUserDto);
    User toEntity(UserDto userDto);
}
