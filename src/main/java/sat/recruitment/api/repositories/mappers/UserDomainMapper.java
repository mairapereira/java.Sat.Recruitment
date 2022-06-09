package sat.recruitment.api.repositories.mappers;

import lombok.experimental.UtilityClass;
import sat.recruitment.api.services.domain.User;
import sat.recruitment.api.services.domain.UserType;

@UtilityClass
public class UserDomainMapper {
    public static final String LINE_SEPARATOR =",";
    public static User lineToUser(String[] line) {
        return User.builder()
                .name(line[0])
                .email(line[1])
                .phone(line[2])
                .address(line[3])
                .userType(UserType.getFromCode(line[4]))
                .money(Double.valueOf(line[5]))
                .build();
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
