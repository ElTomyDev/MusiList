package com.heavydelay.model.mapper;

import org.mapstruct.Mapper;

import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.dto.validation.ValidationUserDto;
import com.heavydelay.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(ValidationUserDto validUserDto);
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
