package sat.recruitment.api.repositories;

import sat.recruitment.api.services.domain.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    List<User> findUsersBy(String email, String phone, String name, String address);

    User createuser(User user);
}
