package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.dto.validation.ValidationUserDto;

public interface IUser {
    UserDto createNewUser(ValidationUserDto userDto);
    UserDto updateUser(ValidationUserDto userDto);
    UserDto showUserById(Integer id);
    void deleteUserById(Integer id);
    boolean existsUserById(Integer id);
    List<UserDto> showAllUsers();
}
