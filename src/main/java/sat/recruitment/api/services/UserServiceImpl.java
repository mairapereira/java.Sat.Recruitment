package sat.recruitment.api.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.repositories.UserDAO;
import sat.recruitment.api.services.domain.User;

import static sat.recruitment.api.services.validations.UserValidator.validateErrors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public void createUser(User newUser) {
		String errors = validateErrors(newUser.getName(), newUser.getEmail(), newUser.getAddress(), newUser.getPhone());

		if (errors != null && errors != "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}

		var usersFound = userDAO.findUsersBy(newUser.getEmail(), newUser.getPhone(), newUser.getName(), newUser.getAddress());

		if (usersFound!=null && !usersFound.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
		}

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

		userDAO.createuser(newUser);
	}

}
