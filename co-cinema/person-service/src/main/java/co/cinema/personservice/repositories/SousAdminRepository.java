package co.cinema.personservice.repositories;

import co.cinema.personservice.models.SousAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousAdminRepository extends JpaRepository<SousAdmin, Integer> {
    
}
