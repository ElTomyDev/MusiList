package com.heavydelay.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.user.UserReturnDto;
import com.heavydelay.model.dto.user.UserUpdateDto;
import com.heavydelay.model.entity.User;
import com.heavydelay.repository.RoleRepository;
import com.heavydelay.repository.UserRepository;
import com.heavydelay.service.IUser;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserImplService implements IUser{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserImplService(UserRepository userRepository,RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public UserReturnDto showUserById(Long id, boolean detailed) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        return detailed ? UserReturnDto.toDetailedDto(user) : UserReturnDto.toBasicDto(user);
    }

    @Override
    public List<UserReturnDto> showAllUsers(boolean detailed) {
        List<User> users = (List<User>) userRepository.findAll();

        // Selección del DTO a usar 
        Function<User, UserReturnDto> mapper = detailed ? UserReturnDto::toDetailedDto : UserReturnDto::toBasicDto;

        // Retorno y mapea la lista con todos los usuarios
        return users.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public UserReturnDto loginUser(UserUpdateDto dto){
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
            () -> new ResourceNotFoundException("The user with Email '" + dto.getEmail() + "' was not found")
        );
        // Comprueba la contraseña
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("password is incorrect!");
        }

        user.setLastConnection(LocalDateTime.now());
        userRepository.save(user);
        return UserReturnDto.toBasicDto(user);
    }
 
    @Override
    public UserReturnDto registerNewUser(UserUpdateDto dto){

        User user = new User();

        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setLastConnection(LocalDateTime.now());

        // Se asigna un rol por defecto que seria 'None'
        user.setRole(roleRepository.findByRoleName("None").orElseThrow(
            () -> new ResourceNotFoundException("Default role not found")
        ));

        // Hasheo de contraseña
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User createdUser = userRepository.save(user);
        return UserReturnDto.toBasicDto(createdUser);
    }

    @Override
    public UserReturnDto changeUserValues(Long id, UserUpdateDto dto){

        if (id == null){
            throw new IllegalArgumentException("ID cannot be null to update a user.");
        }
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setDescription(dto.getDescription());
        user.setRole(roleRepository.findByRoleName(dto.getRoleName()).orElseThrow(
            () -> new ResourceNotFoundException("The Role with name '" + dto.getRoleName() + "' was not found")
        ));
        
        // Actualizo el usuario y lo devuelvo con datos filtrados
        userRepository.save(user);
        return UserReturnDto.toBasicDto(user);

    }

    @Override
    public UserReturnDto changeUserPasswordById(Long id, UserUpdateDto dto){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );

        if(!passwordEncoder.matches(dto.getOldPasword(), user.getPassword())){

            throw new IllegalArgumentException("Old password is incorrect!");
        }

        // Haseo de contraseña
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
        return UserReturnDto.toPasswordDto(user);

    }

    @Override
    public UserReturnDto changeUserEmail(UserUpdateDto dto){
        User user = userRepository.findByEmail(dto.getOldEmail()).orElseThrow(
            () -> new ResourceNotFoundException("The user with Email '" + dto.getOldEmail() + "' was not found")
        );

        user.setEmail(dto.getNewEmail());
        userRepository.save(user);
        return UserReturnDto.toEmailDto(user);
        
    }

}
