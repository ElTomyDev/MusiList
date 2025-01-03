package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.entity.User;

public interface IUser {
    User save(UserDto userDto);
    User findById(Integer id);
    void delete(User user);
    boolean existsById(Integer id);
    List<User> getAll();
}
