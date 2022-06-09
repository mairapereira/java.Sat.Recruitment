package sat.recruitment.api.repositories.mappers;

import lombok.experimental.UtilityClass;
import sat.recruitment.api.services.factory.UserFactory;
import sat.recruitment.api.services.domain.User;
import sat.recruitment.api.services.domain.constants.UserType;

@UtilityClass
public class UserDomainMapper {
    public static final String LINE_SEPARATOR =",";
    public static User lineToUser(String[] line) {
        User user = UserFactory.createUser(line[4]);
        user.setName(line[0]);
        user.setEmail(line[1]);
        user.setPhone(line[2]);
        user.setAddress(line[3]);
        user.setUserType(UserType.getFromCode(line[4]));
        user.setMoney(Double.valueOf(line[5]));
        return user;
    }

    public static String userToLine(User user){
        return new StringBuilder()
                .append(user.getName())
                .append(LINE_SEPARATOR)
                .append(user.getEmail())
                .append(LINE_SEPARATOR)
                .append(user.getPhone())
                .append(LINE_SEPARATOR)
                .append(user.getAddress())
                .append(LINE_SEPARATOR)
                .append(user.getUserType().getCode())
                .append(LINE_SEPARATOR)
                .append(user.getMoney())
                .toString();
    }
}
