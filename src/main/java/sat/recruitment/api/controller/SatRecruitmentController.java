package sat.recruitment.api.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.controllers.dtos.UserDto;

@RestController
@RequestMapping(value = "/api/v1")
public class SatRecruitmentController {

	private List<UserDto> users = new ArrayList<UserDto>();

	@PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody UserDto messageBody) {
		String errors = "";

		validateErrors(messageBody.getName(), messageBody.getEmail(), messageBody.getAddress(), messageBody.getPhone(),
				errors);

		if (errors != null && errors != "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}

		UserDto newUser = new UserDto();
		newUser.setName(messageBody.getName());
		newUser.setEmail(messageBody.getEmail());
		newUser.setAddress(messageBody.getAddress());
		newUser.setPhone(messageBody.getPhone());
		newUser.setUserType(messageBody.getUserType());
		newUser.setMoney(messageBody.getMoney());

		if (newUser.getUserType().equals("Normal")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double percentage = Double.valueOf("0.12");
				// If new user is normal and has more than USD100
				var gif = Double.valueOf(newUser.getMoney()) * percentage;
				newUser.setMoney(newUser.getMoney() + gif);
			}
			if (Double.valueOf(newUser.getMoney()) < 100) {
				if (Double.valueOf(newUser.getMoney()) > 10) {
					var percentage = Double.valueOf("0.8");
					var gif = Double.valueOf(newUser.getMoney()) * percentage;
					newUser.setMoney(newUser.getMoney() + gif);
				}
			}
		}
		if (newUser.getUserType().equals("SuperUser")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double percentage = Double.valueOf("0.20");
				Double gif = Double.valueOf(newUser.getMoney()) * percentage;
				newUser.setMoney(newUser.getMoney() + gif);
			}
		}
		if (newUser.getUserType().equals("Premium")) {
			if (Double.valueOf(newUser.getMoney()) > 100) {
				Double gif = Double.valueOf(newUser.getMoney()) * 2;
				newUser.setMoney(newUser.getMoney() + gif);
			}
		}

//obtener user
		Boolean isDuplicated = false;
		for (UserDto user : users) {

			if (user.getEmail().equals(newUser.getEmail()) || user.getPhone().equals(newUser.getPhone())) {
				isDuplicated = true;
			} else if (user.getName().equals(newUser.getName())) {
				if (user.getAddress().equals(newUser.getAddress())) {
					isDuplicated = true;
				}

			}
		}
		if (isDuplicated) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
		}
	}

	public void validateErrors(String name, String email, String address, String phone, String errors) {
		if (name == null)
			// Validate if Name is null
			errors = "The name is required";
		if (email == null)
			// Validate if Email is null
			errors = errors + " The email is required";
		if (address == null)
			// Validate if Address is null
			errors = errors + " The address is required";
		if (phone == null)
			// Validate if Phone is null
			errors = errors + " The phone is required";
	}

}
