package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.UserDto;

public interface IUser {
    UserDto createNewUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto showUserById(Integer id);
    void deleteUserById(Integer id);
    boolean existsUserById(Integer id);
    List<UserDto> showAllUsers();
}
