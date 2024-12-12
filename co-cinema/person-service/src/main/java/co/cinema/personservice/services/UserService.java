package co.cinema.personservice.services;

import co.cinema.personservice.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepositories userRepositories;
}
