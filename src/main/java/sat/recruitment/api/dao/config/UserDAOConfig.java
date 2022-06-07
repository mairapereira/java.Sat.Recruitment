package sat.recruitment.api.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sat.recruitment.api.dao.UserDAO;
import sat.recruitment.api.dao.UserDAOImpl;

@Configuration
public class UserDAOConfig {

    @Bean
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }
}
