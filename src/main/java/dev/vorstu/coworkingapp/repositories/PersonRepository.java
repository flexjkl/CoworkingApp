package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
