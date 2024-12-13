package com.resort.kingfisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resort.kingfisher.model.Admin;
import com.resort.kingfisher.model.Login;
import com.resort.kingfisher.repo.AdminRepository;
import com.resort.kingfisher.repo.LoginRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final LoginRepository loginRepo;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(LoginRepository loginRepo, AdminRepository adminRepository) {
        this.loginRepo = loginRepo;
        this.adminRepository = adminRepository;
    }

    public Admin saveAdmin(Admin adm) {
        Login log = new Login();
        log.setUsername(adm.getUsername());
        log.setPassword(adm.getPassword());
        log.setRole("ADMIN");
        adm.setRole(log.getRole());
        Admin admsaved = adminRepository.save(adm);
        log.setAdmin(admsaved);
        loginRepo.save(log);
        return admsaved;
    }

    // Retrieve all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Retrieve an Admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // Update an existing Admin
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        // Check if the Admin with the given ID exists
        if (adminRepository.existsById(id)) {
            updatedAdmin.setId(id); // Ensure the ID is set to the correct value
            return adminRepository.save(updatedAdmin);
        } else {
            throw new IllegalArgumentException("Admin with ID " + id + " does not exist.");
        }
    }

    // Delete an Admin by ID
    public void deleteAdmin(Long id) {
        // Check if the Admin with the given ID exists
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Admin with ID " + id + " does not exist.");
        }
    }
}
