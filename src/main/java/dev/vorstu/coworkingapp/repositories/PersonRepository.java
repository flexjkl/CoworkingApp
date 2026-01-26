package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.jpa.users.Person;
import dev.vorstu.coworkingapp.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("""
            select p from Person p
            where (p.id = coalesce(:id, p.id))
            and (lower(p.username) like lower(concat('%', coalesce(:username, ''), '%')))
            and (p.role = coalesce(:role, p.role))
            and (lower(p.firstname) like lower(concat('%', coalesce(:firstname, ''), '%')))
            and (lower(p.secondname) like lower(concat('%', coalesce(:secondname, ''), '%')))
            and (lower(p.lastname) like lower(concat('%', coalesce(:lastname, ''), '%')))
            and (lower(p.email) like lower(concat('%', coalesce(:email, ''), '%')))
            """)
    Page<Person> findAll(
            @Param("id") Long id,
            @Param("username") String username,
            @Param("role") Role role,
            @Param("firstname") String firstname,
            @Param("secondname") String secondname,
            @Param("lastname") String lastname,
            @Param("email") String email,
            Pageable pageable
    );

}
