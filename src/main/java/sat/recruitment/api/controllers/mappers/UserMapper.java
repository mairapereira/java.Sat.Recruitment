package sat.recruitment.api.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sat.recruitment.api.controllers.dtos.UserDto;
import sat.recruitment.api.services.domain.User;
import sat.recruitment.api.services.domain.UserType;


@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Named("toUserType")
    default UserType toUserType(String type){
        return UserType.getFromCode(type);
    }
    @Named("fromUserType")
    default String  toUserType(UserType type){
        return type.getCode();
    }
    @Mapping(source = "userType", target = "userType", qualifiedByName = "toUserType")
    User toDomain(UserDto user);
    @Mapping(source = "userType", target = "userType", qualifiedByName = "fromUserType")
    UserDto toDto(User user);
}
