package co.cinema.personservice.services;

import co.cinema.personservice.models.Person;
import co.cinema.personservice.repositories.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {


    private final PersonRepository personRepo;

    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public Person findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    public Person findByEmail(String email){
        return personRepo.findByEmail(email);
    }

    public Person findById(Integer id) {
        return personRepo.findById(id).orElseThrow();
    }


}
