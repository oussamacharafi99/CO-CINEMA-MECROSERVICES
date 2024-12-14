package co.cinema.personservice.services;

import co.cinema.personservice.enums.Role;
import co.cinema.personservice.enums.Status;
import co.cinema.personservice.models.Admin;
import co.cinema.personservice.models.Manager;
import co.cinema.personservice.repositories.AdminRepositroy;
import co.cinema.personservice.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public ManagerService(ManagerRepository managerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, String> createManager(Manager manager) {
        Map<String, String> map = new HashMap<>();

        try {{
                Set<Role> roles = new HashSet<>();
                roles.add(Role.ROLE_MANAGER);
                String password = passwordEncoder.encode(manager.getPassword());
                manager.setPassword(password);
                manager.setRoles(roles);
                manager.setStatus(Status.ACTIVE);
                managerRepository.save(manager);

                map.put("status", "success");
                map.put("response", "The Manager Account created successfully !");
            }
        } catch (Exception ex) {
            map.put("status", "error");
            map.put("response", "An error occurred: " + ex.getMessage());
        }

        return map;
    }



    public Map<String, String> updateTheManagerAccount(Integer id, Manager manager){
        Map<String,  String> map = new HashMap<>();
        Manager newManager = managerRepository.findById(id).orElseThrow();
        try{
            newManager.setUsername(manager.getUsername());
            String newPassword = passwordEncoder.encode(manager.getPassword());
            newManager.setPassword(newPassword);
            newManager.setEmail(manager.getEmail());
            newManager.setRoles(manager.getRoles());
            managerRepository.save(newManager);
            map.put("response" , "The manager Account updated !");
            return map;
        }catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
            map.put("response", "The manager account not updated by Error !");
            return map;
        }
    }


}
