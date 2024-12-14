package co.cinema.personservice.services;

import co.cinema.personservice.enums.Role;
import co.cinema.personservice.enums.Status;
import co.cinema.personservice.models.Admin;
import co.cinema.personservice.models.SousAdmin;
import co.cinema.personservice.repositories.AdminRepositroy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {


    private final AdminRepositroy adminRepositroy;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepositroy adminRepositroy, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepositroy = adminRepositroy;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public Map<String, String> createTheFirstAccountAuto() {
        Map<String, String> map = new HashMap<>();
        List<Admin> adminList = adminRepositroy.findAll(); // Correction ici

        try {
            if (!adminList.isEmpty()) {
                map.put("response", "The demo Account Already exists !");
            } else {
                Set<Role> roles = new HashSet<>();
                roles.add(Role.ROLE_ADMIN);
                String password = passwordEncoder.encode("demo");

                Admin admin = new Admin();
                admin.setId(1);
                admin.setUsername("demo");
                admin.setEmail("demo@cinema.ma");
                admin.setPassword(password);
                admin.setRoles(roles);
                admin.setStatus(Status.ACTIVE);

                adminRepositroy.save(admin); // Assurez-vous de sauvegarder l'admin !

                map.put("status", "success");
                map.put("response", "The demo Account created successfully !");
            }
        } catch (Exception ex) {
            map.put("status", "error");
            map.put("response", "An error occurred: " + ex.getMessage());
        }

        return map;
    }



    public Map<String, String> updateTheAdminAccount(Integer id, Admin admin){
        Map<String,  String> map = new HashMap<>();
        Admin newAdmin = adminRepositroy.findById(id).orElseThrow();
        try{
            newAdmin.setStatus(Status.ACTIVE);
            newAdmin.setUsername(admin.getUsername());
            String newPassword = passwordEncoder.encode(admin.getPassword());
            newAdmin.setPassword(newPassword);
            newAdmin.setEmail(admin.getEmail());
            newAdmin.setRoles(admin.getRoles());
            adminRepositroy.save(newAdmin);
            map.put("response" , "The Admin Account updated !");
            return map;
        }catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
            map.put("response", "The Admin account not updated by Error !");
            return map;
        }
    }
    
    
}
