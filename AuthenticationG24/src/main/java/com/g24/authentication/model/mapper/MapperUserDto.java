package com.g24.authentication.model.mapper;

import com.g24.authentication.model.dto.UserDto;
import com.g24.authentication.model.entity.Role;
import com.g24.authentication.model.entity.User;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class MapperUserDto
{
	public UserDto toDto(User user)
	{
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), 
				user.getEmail(), user.getDegreeCourse(), 
				user.getUniversity());
	}

	public User toUser(UserDto userDTO) {

		return new User(userDTO.getId(), false, userDTO.getFirstName(), userDTO.getLastName(), 
				userDTO.getEmail(), "", userDTO.getDegreeCourse(), userDTO.getUniversity(),
				new ArrayList<Role>());
	}
}
