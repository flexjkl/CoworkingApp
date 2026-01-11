package dev.vorstu.coworkingapp.repositories;

import dev.vorstu.coworkingapp.entities.users.Person;
import dev.vorstu.coworkingapp.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    @Query("""
            select p from Person p
            where (:id is null or p.id = :id)
            and (:username is null or lower(p.username) like lower(concat('%', :username, '%')))
            and (:role is null or p.role = :role)
            and (:firstname is null or lower(p.firstname) like lower(concat('%', :firstname, '%')))
            and (:secondname is null or lower(p.secondname) like lower(concat('%', :secondname, '%')))
            and (:lastname is null or lower(p.lastname) like lower(concat('%', :lastname, '%')))
            and (:email is null or lower(p.email) like lower(concat('%', :email, '%')))
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
