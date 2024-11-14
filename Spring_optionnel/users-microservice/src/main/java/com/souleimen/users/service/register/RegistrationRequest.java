package com.souleimen.users.service.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegistrationRequest {
	private String username; 
	 private String password; 
	 private String email; 
}
