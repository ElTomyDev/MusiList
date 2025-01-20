package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.user.UserReturnDto;
import com.heavydelay.model.dto.user.UserUpdateDto;

public interface IUser {
    UserReturnDto loginUser(UserUpdateDto loginUserDto);
    UserReturnDto registerNewUser(UserUpdateDto dto);

    UserReturnDto changeUserValuesById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserPasswordById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserNameById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserLastnameById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserUsernameById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserDescriptionById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserStatusById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserRoleById(Long id, UserUpdateDto dto);
    UserReturnDto changeUserEmail(UserUpdateDto dto);
    

    UserReturnDto showUserById(Long id, boolean detailed);
    List<UserReturnDto> showAllUsers(boolean detailed);
    UserReturnDto showUserAdminById(Long id);

    void deleteUserById(Long id);
}
