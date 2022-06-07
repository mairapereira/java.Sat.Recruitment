package sat.recruitment.api.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.dao.mappers.UserDomainMapper;
import sat.recruitment.api.services.domain.User;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Repository
@Slf4j
public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();

		Path filePath = Paths.get("users.txt");

		try (Stream<String> lines = Files.lines(filePath)){
			users = lines
					.map(line -> line.split(","))
					.map(UserDomainMapper::lineToUser)
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Ocurrió un error al leer el archivo",e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A error ocurred when get users from file");
		}

		return users;
	}

	@Override
	public List<User> findUsersBy(String email, String phone, String name, String address) {
		List<User> users = new ArrayList<>();

		URL url = this.getClass().getClassLoader().getResource("users.txt");

		try (Stream<String> lines = Files.lines(Path.of(url.toURI()))){
			users = lines
					.map(line -> line.split(","))
					.map(UserDomainMapper::lineToUser)
					.filter(user -> user.getEmail().equals(email) || user.getPhone().equals(phone) || (user.getName().equals(name) && user.getAddress().equals(address)))
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Ocurrió un error al leer el archivo",e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A error ocurred when get users from file");
		}

		return users;
	}
}
