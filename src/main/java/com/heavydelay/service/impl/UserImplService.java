package com.heavydelay.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.model.dao.UserDao;
import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.entity.User;
import com.heavydelay.model.mapper.UserMapper;
import com.heavydelay.service.IUser;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserImplService implements IUser{

    private UserDao userDao;
    private final UserMapper userMapper;

    public UserImplService(UserDao userDao, UserMapper userMapper){
        this.userDao = userDao;
        this.userMapper = userMapper;
    }
    
    @Transactional(readOnly=true)
    @Override
    public void deleteById(Integer id) {
        if (!userDao.existsById(id)){
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userDao.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return userDao.existsById(id);
    }

    @Override
    public UserDto showById(Integer id) {
        User user = userDao.findById(id).orElseThrow(
            () -> new EntityNotFoundException("User with id " + id + " not found"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> showAll() {
        try{
            List<User> users = (List<User>) userDao.findAll();

            List<UserDto> usersDtos= users.stream()
                                    .map(userMapper::toDto)
                                    .collect(Collectors.toList());

            if(usersDtos != null && !usersDtos.isEmpty()){
                return usersDtos;
            }
            return Collections.emptyList();
        } catch(Exception e){
            throw new DataAccessResourceFailureException("Failed to retrieve users", e);
        }
    }

    @Override
    public UserDto create(UserDto userDto){
        User user = userMapper.toEntity(userDto);
        user.setIdUser(null);

        User createdUser = userDao.save(user);

        return userMapper.toDto(createdUser);
    }

    @Override
    public UserDto update(UserDto userDto){

        if (userDto.getIdUser() == null){
            throw new IllegalArgumentException("ID cannot be null to update a user.");
        }
        User user = userDao.findById(userDto.getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userDto.getIdUser() + " not found"));
        User userUpdate = User.builder()
                        .idUser(user.getIdUser())
                        .name(userDto.getName())
                        .lastname(userDto.getLastname())
                        .username(userDto.getUsername())
                        .email(userDto.getEmail())
                        .description(userDto.getDescription())
                        .password(userDto.getPassword())
                        .status(userDto.getStatus())
                        .createDate(user.getCreateDate())
                        .build();
        
        userDao.save(userUpdate);

        return userMapper.toDto(userUpdate);

    }
}
