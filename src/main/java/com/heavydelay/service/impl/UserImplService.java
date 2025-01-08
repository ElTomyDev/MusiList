package com.heavydelay.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.dto.validation.ValidationUserDto;
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
    public UserDto createNewUser(ValidationUserDto validUserDto){
        User user = userMapper.toEntity(validUserDto);
        user.setIdUser(null);

        User createdUser = userRepository.save(user);

        return userMapper.toDto(createdUser);
    }

    @Override
    public UserDto updateUser(ValidationUserDto validUserDto){

        if (validUserDto.getIdUser() == null){
            throw new IllegalArgumentException("ID cannot be null to update a user.");
        }
        User user = userRepository.findById(validUserDto.getIdUser())
            .orElseThrow(() -> new ResourceNotFoundException("The user with ID '" + validUserDto.getIdUser() + "' was not found")
        );
        User userUpdate = User.builder()
                        .idUser(user.getIdUser()) // queda igual
                        .name(validUserDto.getName())
                        .lastname(validUserDto.getLastname())
                        .username(validUserDto.getUsername())
                        .email(user.getEmail()) // queda igual
                        .description(validUserDto.getDescription())
                        .password(user.getPassword()) // queda igual
                        .status(user.getStatus()) // queda igual
                        .createDate(user.getCreateDate()) // queda igual
                        .build();
        
        // Actualizo el usuario y lo devuelvo con datos filtrados
        userRepository.save(userUpdate);
        return userMapper.toDto(userUpdate);

    }
}
