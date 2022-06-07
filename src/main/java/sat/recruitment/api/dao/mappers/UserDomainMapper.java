package sat.recruitment.api.dao.mappers;

import lombok.experimental.UtilityClass;
import sat.recruitment.api.services.domain.User;
@UtilityClass
public class UserDomainMapper {
    public static User lineToUser(String[] line) {
        return User.builder()
                .name(line[0])
                .email(line[1])
                .phone(line[2])
                .address(line[3])
                .userType(line[4])
                .money(Double.valueOf(line[5]))
                .build();
    }
}
