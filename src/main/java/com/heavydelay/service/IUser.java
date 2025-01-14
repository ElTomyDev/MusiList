package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.user.EmailUserDto;
import com.heavydelay.model.dto.user.LoginUserDto;
import com.heavydelay.model.dto.user.PasswordUserDto;
import com.heavydelay.model.dto.user.PublicUserDto;
import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.dto.user.UpdateUserDto;

public interface IUser {
    PublicUserDto loginUser(LoginUserDto loginUserDto);
    PublicUserDto registerNewUser(RegisterUserDto userDto);
    PublicUserDto changeUserValues(Long id, UpdateUserDto userDto);
    PasswordUserDto changeUserPassword(Long id, PasswordUserDto passwordUserDto);
    EmailUserDto changeUserEmail(EmailUserDto newEmail);
    PublicUserDto showUserById(Long id);
    void deleteUserById(Long id);
    List<PublicUserDto> showAllUsers();
}
