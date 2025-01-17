package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.user.UserReturnDto;
import com.heavydelay.model.dto.user.UserUpdateDto;

public interface IUser {
    UserReturnDto loginUser(UserUpdateDto loginUserDto);
    UserReturnDto registerNewUser(UserUpdateDto dto);
    UserReturnDto changeUserValues(Long id, UserUpdateDto dto);
    UserReturnDto changeUserPasswordById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserEmail(UserUpdateDto dto);
    UserReturnDto showUserById(Long id, boolean detailed);
    void deleteUserById(Long id);
    List<UserReturnDto> showAllUsers(boolean detailed);
}
