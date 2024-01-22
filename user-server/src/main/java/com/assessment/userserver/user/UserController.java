package com.assessment.userserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		UserDTO user = userService.getUserById(id);
		return (user != null) ?
				new ResponseEntity<>(user, HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		UserDTO createdUser = userService.createUser(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/generate-token/{id}")
	public ResponseEntity<String> generateToken(@PathVariable Long id) {
		String token = userService.generateTokenForUser(id);
		return (token != null) ?
				new ResponseEntity<>(token, HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/validate-token")
	public ResponseEntity<Void> validateToken(@RequestBody String token) {
		boolean isValid = userService.validateTokenForUser(token);
		return (isValid) ?
				new ResponseEntity<>(HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}