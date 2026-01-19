package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.entities.users.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "password", source = "encodedPassword")
    Admin toEntity(UserCreationDTO creationDTO, String encodedPassword);

}
