package com.assessment.userserver.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The UserController class handles requests related to user operations.
 */
@RestController
@RequestMapping("user-server/v1//users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns a ResponseEntity containing a list of UserDTO objects representing all users.
	 *
	 * @return a ResponseEntity containing a list of UserDTO objects
	 */
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id the ID of the user
	 * @return a ResponseEntity containing the UserDTO object representing the user if found, or a ResponseEntity
	 *         with status NOT_FOUND if the user is not found
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		UserDTO user = userService.getUserById(id);
		return (user != null) ?
				new ResponseEntity<>(user, HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Creates a new user.
	 *
	 * @param userDTO the UserDTO object representing the user to be created
	 * @return a ResponseEntity containing the UserDTO object representing the created user and a HTTP status code of 201 (CREATED)
	 */
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		UserDTO createdUser = userService.createUser(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	/**
	 * Generates a token for a user.
	 *
	 * @param id the ID of the user
	 * @return a ResponseEntity containing the generated token if successfully generated,
	 *         or a ResponseEntity with status NOT_FOUND if the user is not found
	 */
	@PostMapping("/generate-token/{id}")
	public ResponseEntity<String> generateToken(@PathVariable Long id) {
		String token = userService.generateTokenForUser(id);
		return (token != null) ?
				new ResponseEntity<>(token, HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Validates the given token for a user.
	 *
	 * @param token the token to be validated
	 * @return a ResponseEntity with status OK if the token is valid, or a ResponseEntity
	 *         with status UNAUTHORIZED if the token is invalid
	 */
	@PostMapping("/validate-token")
	public ResponseEntity<Void> validateToken(@RequestBody String token) {
		boolean isValid = userService.validateTokenForUser(token);
		return (isValid) ?
				new ResponseEntity<>(HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}