package com.repoUnsij.usuarios.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	
	private int code;
	private String description;
}

