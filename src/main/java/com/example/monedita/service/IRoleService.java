package com.example.monedita.service;

import com.example.monedita.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save (Role role);
    void delete(Role role);
    Role update(Role role);
}
