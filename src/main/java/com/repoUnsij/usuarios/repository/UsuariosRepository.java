package com.repoUnsij.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.repoUnsij.usuarios.entites.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
}
