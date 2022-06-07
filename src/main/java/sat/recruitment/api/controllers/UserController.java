package sat.recruitment.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sat.recruitment.api.controllers.dtos.UserDto;
import sat.recruitment.api.controllers.mappers.UserMapper;
import sat.recruitment.api.services.UserService;

@RestController
@RequestMapping(value = "/api/v1/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody UserDto user) {
		userService.createUser(UserMapper.INSTANCE.toDomain(user));
	}
}
