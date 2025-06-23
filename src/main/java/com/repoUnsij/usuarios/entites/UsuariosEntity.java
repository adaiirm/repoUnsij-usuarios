package com.repoUnsij.usuarios.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UsuariosEntity {
    
    @Id
    private Long id;
    private String nombre;
    private String correo;
    private Boolean activo;
    private Long rol_id;
}
