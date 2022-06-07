package sat.recruitment.api.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sat.recruitment.api.dao.UserDAO;
import sat.recruitment.api.services.UserService;
import sat.recruitment.api.services.UserServiceImpl;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserService userService(UserDAO userDAO){
        return new UserServiceImpl(userDAO);
    }
}
