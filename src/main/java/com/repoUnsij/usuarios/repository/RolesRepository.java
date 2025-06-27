package com.repoUnsij.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repoUnsij.usuarios.entites.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    
}
