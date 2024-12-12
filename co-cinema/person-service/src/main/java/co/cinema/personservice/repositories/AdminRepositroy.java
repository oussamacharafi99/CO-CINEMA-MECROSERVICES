package co.cinema.personservice.repositories;
import co.cinema.personservice.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepositroy extends JpaRepository<Admin, Integer> {
}
