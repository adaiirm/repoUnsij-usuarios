package com.repoUnsij.usuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repoUnsij.usuarios.entites.Usuarios;
import com.repoUnsij.usuarios.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    // Obtener todos los usuarios
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    public Optional<Usuarios> getUsuarioById(Long id) {
        return usuariosRepository.findById(id);
    }

    // Crear un usuario
    public Usuarios createUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    // Actualizar usuario por id
    public Optional<Usuarios> updateUsuario(Long id, Usuarios usuarioDetails) {
        
        return usuariosRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setContraseña(usuarioDetails.getContraseña());
            usuario.setActivo(usuarioDetails.getActivo());
            usuario.setRol(usuarioDetails.getRol());

            return usuariosRepository.save(usuario);
        });
    }

    // Eliminar un usuario por id
    public boolean deleteUsuario(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
