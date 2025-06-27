package com.repoUnsij.usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repoUnsij.usuarios.entites.Roles;
import com.repoUnsij.usuarios.response.ErrorResponse;
import com.repoUnsij.usuarios.services.RolesService;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    
    @Autowired
    private RolesService rolesService;
    
    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        try {
            List<Roles> response = rolesService.getAllRoles();
            if (response.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(404, "No se encontraron roles"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
