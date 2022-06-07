package sat.recruitment.api.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sat.recruitment.api.controllers.dtos.UserDto;
import sat.recruitment.api.services.domain.User;


@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toDomain(UserDto user);
    UserDto toDto(User user);
}
