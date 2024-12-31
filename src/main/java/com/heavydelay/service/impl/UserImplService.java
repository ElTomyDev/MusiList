package com.heavydelay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.model.dao.UserDao;
import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.entity.User;
import com.heavydelay.service.IUser;

@Service
public class UserImplService implements IUser{

    @Autowired
    private UserDao userDao;
    
    @Transactional(readOnly=true)
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean existsById(Integer id) {
        return userDao.findById(id) != null;
    }
    
    @Override
    public User findById(Integer id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public List<User> listAll() {
        return (List) userDao.findAll();
    }

    @Override
    public User save(UserDto userDto) {
        User user = User.builder()
                    .idUser(userDto.getIdUser())
                    .name(userDto.getName())
                    .lastname(userDto.getLastname())
                    .username(userDto.getUsername())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .createDate(userDto.getCreateDate())
                    .isAdmin(userDto.getIsAdmin())
                    .idRole(userDto.getIdRole())
                    .idBand(userDto.getIdBand()).build();
        return userDao.save(user);
    }

}
