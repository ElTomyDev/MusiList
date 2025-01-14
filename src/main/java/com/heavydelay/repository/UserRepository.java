package com.heavydelay.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
