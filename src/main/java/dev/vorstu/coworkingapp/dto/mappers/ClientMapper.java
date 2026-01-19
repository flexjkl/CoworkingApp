package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.UserCreationDTO;
import dev.vorstu.coworkingapp.entities.users.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "password", source = "encodedPassword")
    Client toEntity(UserCreationDTO creationDTO, String encodedPassword);

}
