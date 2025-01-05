package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.entity.User;

public interface IUser {
    User save(UserDto userDto);
    UserDto showById(Integer id);
    void deleteById(Integer id);
    boolean existsById(Integer id);
    List<User> showAll();
}
