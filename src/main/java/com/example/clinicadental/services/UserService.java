package com.example.clinicadental.services;

import com.example.clinicadental.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}

