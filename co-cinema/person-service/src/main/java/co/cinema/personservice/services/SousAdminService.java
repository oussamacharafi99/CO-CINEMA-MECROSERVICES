package co.cinema.personservice.services;

import co.cinema.personservice.enums.Status;
import co.cinema.personservice.models.SousAdmin;
import co.cinema.personservice.repositories.SousAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SousAdminService {

    @Autowired
    private SousAdminRepository sousAdminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Map<String, String> createSousAdmin(SousAdmin sousAdmin) {
        Map<String, String> response = new HashMap<>();
        try {
            String encodedPassword = passwordEncoder.encode(sousAdmin.getPassword());
            sousAdmin.setPassword(encodedPassword);

            sousAdminRepository.save(sousAdmin);
            response.put("status", "success");
            response.put("message", "Admin system created successfully.");
        } catch (Exception ex) {

            System.out.println("Error: " + ex.getMessage());
            response.put("status", "error");
            response.put("message", "Failed to create admin system: " + ex.getMessage());
        }
        return response;
    }

    public Map<String, String> updateSousAdmin(Integer id, SousAdmin updateSousAdmin) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<SousAdmin> newSousAdmin = sousAdminRepository.findById(id);
            if (newSousAdmin.isPresent()) {
                SousAdmin existingAdminSystem = newSousAdmin.get();

                existingAdminSystem.setUsername(updateSousAdmin.getUsername());
                if (updateSousAdmin.getPassword() != null && !updateSousAdmin.getPassword().isEmpty()) {
                    String encodedPassword = passwordEncoder.encode(updateSousAdmin.getPassword());
                    existingAdminSystem.setPassword(encodedPassword);
                }
                existingAdminSystem.setEmail(updateSousAdmin.getEmail());


                sousAdminRepository.save(existingAdminSystem);

                response.put("status", "success");
                response.put("message", "Sous Admin  updated successfully.");
            } else {
                response.put("status", "error");
                response.put("message", "Sous Admin  not found with ID: " + id);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            response.put("status", "error");
            response.put("message", "Failed to update admin system: " + ex.getMessage());
        }
        return response;
    }

    public Map<String, String> deleteSousAdmin(Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<SousAdmin> optionalSousAdmin = sousAdminRepository.findById(id);
            if (optionalSousAdmin.isPresent()) {
                sousAdminRepository.deleteById(id);
                response.put("status", "success");
                response.put("message", "Sous system deleted successfully.");
            } else {
                response.put("status", "error");
                response.put("message", "Sous system not found with ID: " + id);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            response.put("status", "error");
            response.put("message", "Failed to delete sous admin : " + ex.getMessage());
        }
        return response;
    }


    public List<SousAdmin> getAllSousAdmin() {
        try {
            return sousAdminRepository.findAll();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return Collections.emptyList();
        }
    }

    public Map<String, String> changeStatusOfAdminSystem(Integer id, Status status){
        Map<String, String> response = new HashMap<>();
        try {
            SousAdmin sousAdmin = sousAdminRepository.findById(id).orElseThrow();
            if (status == Status.ACTIVE){
                sousAdmin.setStatus(status);
                response.put("status", "success");
                response.put("message", "the Account is active now !");
            }
            else if(status == Status.INACTIVE){
                sousAdmin.setStatus(status);
                response.put("status", "success");
                response.put("message", "the Account is inactive now !");
            }

        }catch (Exception ex){
            response.put("message", "the Account already active !");
            response.put("status", ex.getMessage());
        }
        return response;
    }
}
