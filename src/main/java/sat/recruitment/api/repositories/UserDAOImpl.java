package sat.recruitment.api.repositories;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.repositories.mappers.UserDomainMapper;
import sat.recruitment.api.services.domain.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sat.recruitment.api.repositories.mappers.UserDomainMapper.LINE_SEPARATOR;

@Repository
@Slf4j
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        URL url = this.getClass().getClassLoader().getResource("users.txt");

        try (Stream<String> lines = Files.lines(Path.of(url.toURI()))) {
            users = lines
                    .map(line -> line.split(","))
                    .map(UserDomainMapper::lineToUser)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Ocurrió un error al leer el archivo", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A error ocurred when get users from file");
        }

        return users;
    }

    @Override
    public List<User> findUsersBy(String email, String phone, String name, String address) {
        List<User> users = new ArrayList<>();

        URL url = this.getClass().getClassLoader().getResource("users.txt");

        try (Stream<String> lines = Files.lines(Path.of(url.toURI()))) {
            users = lines
                    .map(line -> line.split(LINE_SEPARATOR))
                    .map(UserDomainMapper::lineToUser)
                    .filter(user -> user.getEmail().equals(email) || user.getPhone().equals(phone) || (user.getName().equals(name) && user.getAddress().equals(address)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Ocurrió un error al leer el archivo", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A error ocurred when get users from file");
        }

        return users;
    }

    @Override
    public User createuser(User user) {
        String line = UserDomainMapper.userToLine(user);
        log.info("Adding user {}", line);
        URL url = this.getClass().getClassLoader().getResource("users.txt");

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Path.of(url.toURI()).toFile(), true))) {
            writer.newLine();
            writer.write(line);
        } catch (IOException e) {
            log.error("Ocurrió un error al leer el archivo", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A error ocurred when write user to file");
        } catch (URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A error ocurred when write user to file");
        }
        return user;
    }
}
