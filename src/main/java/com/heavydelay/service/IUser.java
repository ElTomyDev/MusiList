package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.user.PasswordUserDto;
import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.dto.user.UpdateUserDto;
import com.heavydelay.model.dto.user.UserDto;

public interface IUser {
    UserDto loginUser()
    UserDto registerNewUser(RegisterUserDto userDto);
    UserDto changeUserValues(Integer id, UpdateUserDto userDto);
    PasswordUserDto changeUserPassword(Integer id, PasswordUserDto passwordUserDto);
    UserDto showUserById(Integer id);
    void deleteUserById(Integer id);
    boolean existsUserById(Integer id);
    List<UserDto> showAllUsers();
}
