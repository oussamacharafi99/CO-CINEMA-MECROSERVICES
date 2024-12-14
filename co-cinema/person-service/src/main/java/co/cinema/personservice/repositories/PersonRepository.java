package co.cinema.personservice.repositories;

import co.cinema.personservice.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query(value = "SELECT p from Person p where p.username = :username")
    Person findByUsername(@Param("username") String username);
    Person findByEmail(String email);
}
