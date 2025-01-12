package com.heavydelay.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.user.PasswordUserDto;
import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.dto.user.UpdateUserDto;
import com.heavydelay.model.dto.user.UserDto;
import com.heavydelay.model.entity.User;
import com.heavydelay.model.mapper.UserMapper;
import com.heavydelay.repository.UserRepository;
import com.heavydelay.service.IUser;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserImplService implements IUser{

    private UserRepository userRepository;
    private final UserMapper userMapper;

    public UserImplService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
    @Transactional(readOnly=true)
    @Override
    public void deleteUserById(Integer id) {
        if (!userRepository.existsById(id)){
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsUserById(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDto showUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> showAllUsers() {
        try{
            List<User> users = (List<User>) userRepository.findAll();

            List<UserDto> usersDtos = users.stream()
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
    public UserDto registerNewUser(RegisterUserDto registerUserDto){

        User createdUser = userRepository.save(userMapper.toEntity(registerUserDto));

        return userMapper.toDto(createdUser);
    }
    

    @Override
    public UserDto changeUserValues(Integer id, UpdateUserDto updateUserDto){

        if (id == null){
            throw new IllegalArgumentException("ID cannot be null to update a user.");
        }
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        user.setName(updateUserDto.getName());
        user.setLastname(updateUserDto.getLastname());
        user.setUsername(updateUserDto.getUsername());
        user.setDescription(updateUserDto.getDescription());
        
        // Actualizo el usuario y lo devuelvo con datos filtrados
        userRepository.save(user);
        return userMapper.toDto(user);

    }

    @Override
    public PasswordUserDto changeUserPassword(Integer id, PasswordUserDto passwordUserDto){

        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        if(!passwordUserDto.getOldPasword().equals(user.getPassword())){
            throw new IllegalArgumentException("Old password is incorrect!");
        }

        user.setPassword(passwordUserDto.getNewPasword());
        
        userRepository.save(user);
        return passwordUserDto;

    }


}
