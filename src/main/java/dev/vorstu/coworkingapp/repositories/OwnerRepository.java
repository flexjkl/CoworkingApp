package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.users.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {



}
