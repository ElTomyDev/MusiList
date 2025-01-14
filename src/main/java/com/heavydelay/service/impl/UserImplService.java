package com.heavydelay.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserImplService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional(readOnly=false)
    @Override
    public void deleteUserById(Long id) {
        User userDelete = userRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("User with id " + id + " not found")
        );
        userRepository.delete(userDelete);
    }

    @Override
    public PublicUserDto showUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        return userMapper.toPublicDto(user);
    }

    @Override
    public List<PublicUserDto> showAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();

        // Retorno y mapea la lista con todos los usuarios
        return users.stream().map(userMapper::toPublicDto).collect(Collectors.toList());
    }

    @Override
    public PublicUserDto loginUser(LoginUserDto loginUserDto){
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(
            () -> new ResourceNotFoundException("The user with Email '" + loginUserDto.getEmail() + "' was not found")
        );
        // Comprueba la contraseña
        if (!passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("password is incorrect!");
        }

        user.setLastConnection(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toPublicDto(user);
    }

    @Override
    public PublicUserDto registerNewUser(RegisterUserDto registerUserDto){

        User user = userMapper.toRegisterEntity(registerUserDto);

        // Hasheo de contraseña
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        User createdUser = userRepository.save(user);
        return userMapper.toPublicDto(createdUser);
    }

    @Override
    public PublicUserDto changeUserValues(Long id, UpdateUserDto updateUserDto){

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
    public PasswordUserDto changeUserPassword(Long id, PasswordUserDto passwordUserDto){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );

        if(!passwordEncoder.matches(passwordUserDto.getOldPasword(), user.getPassword())){
            throw new IllegalArgumentException("Old password is incorrect!");
        }

        // Haseo de contraseña
        user.setPassword(passwordEncoder.encode(passwordUserDto.getNewPasword()));
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
