package com.assessment.userserver.user;

import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDTO userToUserDTO(User user);

	User userDTOToUser(UserDTO userDTO);

	List<UserDTO> usersToUserDTOs(List<User> users);
}
