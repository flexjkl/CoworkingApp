package dev.vorstu.coworkingapp.dto.output.mappers;

import dev.vorstu.coworkingapp.dto.output.PersonOutputDTO;
import dev.vorstu.coworkingapp.entities.users.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonOutputMapper {

    PersonOutputDTO toDTO(Person person);

    List<PersonOutputDTO> listToDTO(List<Person> persons);

}
