package com.repoUnsij.usuarios.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity (name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String contrasenia;
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carreras carrera;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Roles rol;
}
