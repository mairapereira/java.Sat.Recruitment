package sat.recruitment.api.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sat.recruitment.api.controllers.dtos.UserDto;
import sat.recruitment.api.services.factory.UserFactory;
import sat.recruitment.api.services.domain.User;
import sat.recruitment.api.services.domain.UserType;


@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Named("fromUserType")
    default String  toUserType(UserType type){
        return type.getCode();
    }
    @Mapping(source = "userType", target = "userType", qualifiedByName = "fromUserType")
    UserDto toDto(User user);

    default User toDomain(UserDto userDto){
        User user= UserFactory.createUser(userDto.getUserType());
        user.setName(userDto.getName());
        user.setMoney(userDto.getMoney());
        user.setUserType(UserType.getFromCode(userDto.getUserType()));
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return user;
    }
}
