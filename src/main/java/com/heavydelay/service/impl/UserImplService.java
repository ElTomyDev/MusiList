package com.heavydelay.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.user.EmailUserDto;
import com.heavydelay.model.dto.user.LoginUserDto;
import com.heavydelay.model.dto.user.PasswordUserDto;
import com.heavydelay.model.dto.user.PublicUserDto;
import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.dto.user.UpdateUserDto;
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
    public boolean checkPassword(String comparePassword, String passwordCompared){
        return comparePassword.equals(passwordCompared);
    }

    @Override
    public PublicUserDto showUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        return userMapper.toPublicDto(user);
    }

    @Override
    public List<PublicUserDto> showAllUsers() {
        try{
            List<User> users = (List<User>) userRepository.findAll();

            List<PublicUserDto> usersDtos = users.stream()
                                    .map(userMapper::toPublicDto)
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
    public PublicUserDto loginUser(LoginUserDto loginUserDto){
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(
            () -> new ResourceNotFoundException("The user with Email '" + loginUserDto.getEmail() + "' was not found")
        );

        if (!this.checkPassword(user.getPassword(), loginUserDto.getPassword())){
            throw new IllegalArgumentException("password is incorrect!");
        }
        user.setLastConnection(LocalDateTime.now());
        userRepository.save(user);

        return userMapper.toPublicDto(user);
    }

    @Override
    public PublicUserDto registerNewUser(RegisterUserDto registerUserDto){

        User createdUser = userRepository.save(userMapper.toRegisterEntity(registerUserDto));

        return userMapper.toPublicDto(createdUser);
    }

    @Override
    public PublicUserDto changeUserValues(Integer id, UpdateUserDto updateUserDto){

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
        return userMapper.toPublicDto(user);

    }

    @Override
    public PasswordUserDto changeUserPassword(Integer id, PasswordUserDto passwordUserDto){

        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        if(!this.checkPassword(user.getPassword(), passwordUserDto.getOldPasword())){
            throw new IllegalArgumentException("Old password is incorrect!");
        }

        user.setPassword(passwordUserDto.getNewPasword());
        
        userRepository.save(user);
        return passwordUserDto;

    }

    @Override
    public EmailUserDto changeUserEmail(EmailUserDto newEmail){
        User user = userRepository.findByEmail(newEmail.getOldEmail()).orElseThrow(
            () -> new ResourceNotFoundException("The user with Email '" + newEmail.getOldEmail() + "' was not found")
        );
        user.setEmail(newEmail.getNewEmail());
        return newEmail;
        
    }

}
