package com.assessment.userserver.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {
	private UUID id;
	private String username;

	// Constructors, getters, setters
}