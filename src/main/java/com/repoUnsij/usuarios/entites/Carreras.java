package com.repoUnsij.usuarios.entites;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Carreras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(length = 10)
    private String codigo;

    @OneToMany(mappedBy = "carrera")
    @JsonBackReference
    private List<Usuarios> usuarios;
        
    // Constructores
    public Carreras() {}
    
    public Carreras(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
}
