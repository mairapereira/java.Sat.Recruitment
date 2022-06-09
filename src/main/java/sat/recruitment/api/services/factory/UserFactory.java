package sat.recruitment.api.services.factory;

import sat.recruitment.api.services.domain.*;
import sat.recruitment.api.services.domain.constants.UserType;

public class UserFactory {

    public static User createUser(String userType) {
        if (UserType.NORMAL.getCode().equalsIgnoreCase(userType)) {
            return NormalUser.builder().build();
        } else if (UserType.SUPER.getCode().equalsIgnoreCase(userType)) {
            return SuperUser.builder().build();
        } else if (UserType.PREMIUM.getCode().equalsIgnoreCase(userType)) {
            return PremiumUser.builder().build();
        }
        return null;
    }

}