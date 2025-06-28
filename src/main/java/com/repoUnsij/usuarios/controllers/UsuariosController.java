package com.repoUnsij.usuarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repoUnsij.usuarios.entites.Usuarios;
import com.repoUnsij.usuarios.response.ErrorResponse;
import com.repoUnsij.usuarios.services.UsuariosService;

//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;

    @GetMapping
    private ResponseEntity<?> getAllUsers() {
        
    	try {
    		List<Usuarios> response = usuariosService.getAllUsuarios();    		
    		if (response.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(404, "No se encontraron registros"), HttpStatus.NOT_FOUND);
            }
    		
    		return new ResponseEntity<>(response, HttpStatus.OK);
    		
    	} catch ( Exception e ) {
			System.err.println(e);
    		ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
    		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

	@GetMapping("/obtenerPorId/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
		try {
			Optional<Usuarios> response = usuariosService.getUsuarioById(id);

			if (response.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(404, "No se encontro al usuario"), HttpStatus.NOT_FOUND);
            }

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e){
			System.err.println(e);
    		ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
    		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuarios usuario) {
        try {
            Optional<Usuarios> nuevoUsuario = usuariosService.createUsuarioIfCorreoNotExists(usuario);
            if (nuevoUsuario.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(400, "El correo ya ha sido registrado anteriormente"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(nuevoUsuario.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(500, "Error al crear usuario"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioDetails) {
		Optional<Usuarios> usuario = usuariosService.getUsuarioById(id);
		try{
			if (usuario.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(404, "No se encontro al usuario"), HttpStatus.NOT_FOUND);
            }

			Optional<Usuarios> response = usuariosService.updateUsuario(id, usuarioDetails);

			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e) {
			System.err.println(e);
    		ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
    		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        boolean deleted = usuariosService.deleteUsuario(id);
        if (deleted) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse(404, "Usuario no encontrado para eliminar"), HttpStatus.NOT_FOUND);
        }
    }

	@GetMapping("/desactivar/{id}")
	public ResponseEntity<?> desactivarCuenta(@PathVariable Long id) {
		return cambiarEstadoCuenta(id, false);
	}

	@GetMapping("/activar/{id}")
	public ResponseEntity<?> activarCuenta(@PathVariable Long id) {
		return cambiarEstadoCuenta(id, true);
	}

	private ResponseEntity<?> cambiarEstadoCuenta(Long id, boolean estadoActivo) {
		try {
			Optional<Usuarios> usuario = usuariosService.getUsuarioById(id);

			if (usuario.isEmpty()) {
				return new ResponseEntity<>(new ErrorResponse(404, "No se encontró al usuario"), HttpStatus.NOT_FOUND);
			}

			Usuarios usuarioUpdate = usuario.get();
			usuarioUpdate.setActivo(estadoActivo);

			Optional<Usuarios> response = usuariosService.updateUsuario(id, usuarioUpdate);

			if (response.isPresent()) {
				return new ResponseEntity<>(response.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ErrorResponse(500, "No se pudo actualizar el usuario"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			System.err.println(e);
			ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
