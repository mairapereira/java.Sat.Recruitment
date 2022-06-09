package platform.messagingservice.factory;

import lombok.experimental.UtilityClass;
import sat.recruitment.api.services.domain.User;
import sat.recruitment.api.services.domain.constants.UserType;
import sat.recruitment.api.services.factory.UserFactory;

@UtilityClass
public class UserMockFactory {
    public static User mockNormalUser(){
        var user = UserFactory.createUser(UserType.NORMAL.getCode());
        user.setUserType(UserType.NORMAL);
        user.setMoney(80d);
        user.setName("Maria");
        user.setEmail("maria@mail.com");
        user.setPhone("123456789");
        user.setAddress("Cacique lambare");

        return user;
    }

}
