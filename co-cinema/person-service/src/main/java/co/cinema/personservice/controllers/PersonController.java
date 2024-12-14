package co.cinema.personservice.controllers;

import co.cinema.personservice.config.JwtAuth;
import co.cinema.personservice.dto.JwtDto;
import co.cinema.personservice.models.Admin;
import co.cinema.personservice.models.Person;
import co.cinema.personservice.services.AdminService;
import co.cinema.personservice.services.PersonService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class PersonController {
    private final AuthenticationManager authenticationManager;
    private final PersonService personService;
    private final AdminService adminService;

    public PersonController(AuthenticationManager authenticationManager, PersonService personService, AdminService adminService) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.adminService = adminService;
    }


    @PostMapping("/login")
    public JwtDto login(@RequestBody Person personLogin) {

        if (personLogin.getUsername().contains("@")){
            Person personEmail = personService.findByEmail(personLogin.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(personEmail.getUsername(), personLogin.getPassword())
            );
            Person person = personService.findByUsername(personEmail.getUsername());
            System.out.println(person);
            Set<String> roles = person.getRoles().stream()
                    .map(role -> role.name())
                    .collect(Collectors.toSet());
            Integer personId = person.getId();
            String token = JwtAuth.generateToken(personLogin.getUsername(), roles);
            System.out.println("personId : " + personId);
            return new JwtDto(personId , token);
        }
        else{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(personLogin.getUsername(), personLogin.getPassword())
            );
            Person person = personService.findByUsername(personLogin.getUsername());
            Set<String> roles = person.getRoles().stream()
                    .map(role -> role.name())
                    .collect(Collectors.toSet());
            Integer personId = person.getId();
            String token = JwtAuth.generateToken(personLogin.getUsername(), roles);
            System.out.println("personId : " + personId);
            return new JwtDto(personId , token);
        }
    }

    @PutMapping("update/{id}")
    public Map<String, String> updateAdmin(@PathVariable Integer id, @RequestBody Admin admin){
        return adminService.updateTheAdminAccount(id, admin);
    }
}
