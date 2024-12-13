package com.resort.kingfisher.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.kingfisher.model.Login;
import com.resort.kingfisher.model.Owner;
import com.resort.kingfisher.repo.LoginRepository;
import com.resort.kingfisher.repo.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
	 @Autowired
	    private LoginRepository loginRepo;
    @Autowired
    private OwnerRepository ownerRepository;

    // Create a new Owner

    public Owner saveowner(Owner owner) {
        Login log = new Login();
        log.setUsername(owner.getUsername());
        log.setPassword(owner.getPassword());
        log.setRole("OWNER");
        owner.setRole(log.getRole());
        Owner ownerSaved = ownerRepository.save(owner);
        log.setOwner(ownerSaved);
        loginRepo.save(log);
        return ownerSaved;
    }

    // Retrieve all Owners
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // Retrieve an Owner by ID
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).get() ;
    }
 // Update an existing Owner
    public Owner updateOwner(Long id, Owner updatedOwner) {
        // Check if the Owner with the given ID exists
        if (ownerRepository.existsById(id)) {
            updatedOwner.setId(id); // Ensure the ID is set to the correct value
            return ownerRepository.save(updatedOwner);
        } else {
            throw new IllegalArgumentException("Owner with ID " + id + " does not exist.");
        }
    }

    // Delete an Owner by ID
    public void deleteOwner(Long id) {
        // Check if the Owner with the given ID exists
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Owner with ID " + id + " does not exist.");
        }
    }
}
