package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.jpa.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>{

    @Query("""
            select c from Client c
            join fetch c.reviews
            where c.id = :id
            """)
    Optional<Client> findByIdWithReviews(@Param("id") Long id);

}
