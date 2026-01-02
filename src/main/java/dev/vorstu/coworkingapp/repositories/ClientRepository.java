package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {



}
