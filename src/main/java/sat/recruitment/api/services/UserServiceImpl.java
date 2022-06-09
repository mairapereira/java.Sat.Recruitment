package sat.recruitment.api.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.repositories.UserDAO;
import sat.recruitment.api.services.domain.User;

import static sat.recruitment.api.services.domain.constants.Errors.USER_DUPLICATE;
import static sat.recruitment.api.services.validations.UserValidator.validateErrors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public User createUser(User newUser) {
		String errors = validateErrors(newUser.getName(), newUser.getEmail(), newUser.getAddress(), newUser.getPhone());

		if (errors != null && errors != "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}

		var usersFound = userDAO.findUsersBy(newUser.getEmail(), newUser.getPhone(), newUser.getName(), newUser.getAddress());

		if (usersFound!=null && !usersFound.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_DUPLICATE.getDescription());
		}
		newUser.chargeGift();
		return userDAO.createuser(newUser);
	}

}
