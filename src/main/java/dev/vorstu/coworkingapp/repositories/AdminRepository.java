package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.entities.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
