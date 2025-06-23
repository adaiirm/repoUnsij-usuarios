package com.repoUnsij.usuarios.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repoUnsij.usuarios.response.ErrorResponse;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/usuarios")
public class Usuarios {
	/* 
    @GetMapping("/mostrarTodos")
    private ResponseEntity<?> getAllUsers() {
        
    	try {

    		//List<ReviewDTO> response = reviewService.getAverageCalifications(hotelIds);
    		
    		if (response.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(404, "No se encontraron registros"), HttpStatus.NOT_FOUND);
            }
    		
    		return new ResponseEntity<>(response, HttpStatus.OK);
    		
    	} catch ( Exception e ) {
    		ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
    		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
		*/
}
