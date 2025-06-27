package com.repoUnsij.usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repoUnsij.usuarios.entites.Roles;
import com.repoUnsij.usuarios.repository.RolesRepository;

@Service
public class RolesService {
    
    @Autowired
    private RolesRepository rolesRepository;

    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }
}
