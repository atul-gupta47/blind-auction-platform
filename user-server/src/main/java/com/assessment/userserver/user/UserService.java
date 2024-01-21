package com.assessment.userserver.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final TokenService tokenService;


	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return userMapper.usersToUserDTOs(users);
	}

	public UserDTO getUserById(UUID id) {
		User user = userRepository.findById(id).orElse(null);
		return (user != null) ? userMapper.userToUserDTO(user) : null;
	}

	public UserDTO createUser(UserDTO userDTO) {
		User user = userMapper.userDTOToUser(userDTO);
		User savedUser = userRepository.save(user);
		return userMapper.userToUserDTO(savedUser);
	}

	public String generateTokenForUser(UUID userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			String token = tokenService.generateToken(userId);
			user.setToken(token);
			userRepository.save(user);
			return token;
		}
		return null;
	}

	public boolean validateTokenForUser(UUID userId, String token) {
		User user = userRepository.findById(userId).orElse(null);
		return (user != null) && tokenService.validateToken(userId, token);
	}
}