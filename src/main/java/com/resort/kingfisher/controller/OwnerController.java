package com.resort.kingfisher.controller;

import com.resort.kingfisher.model.Owner;
import com.resort.kingfisher.service.CheckLoginService;
import com.resort.kingfisher.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners") 
// Base URL for owner-related operations
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private CheckLoginService loginService;
    // Create a new Owner
    

    // Retrieve all Owners
    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    // Retrieve an Owner by ID
    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

 // Edit owner endpoint
    @GetMapping("/editOwner/{id}")
    public String showEditOwnerForm(@PathVariable("id") Long id, Model model) {
        Owner owner = ownerService.getOwnerById(id);
        model.addAttribute("owner", owner);
        return "editowner";
    }

    // Update owner endpoint
    @PostMapping("/updateOwner")
    public String updateOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.updateOwner(owner.getId(), owner);
        return "redirect:/owners";
    }

 // Delete owner endpoint
    @GetMapping("/deleteOwner/{id}")
    public String deleteOwner(@PathVariable("id") Long id, Model model) {
        try {
            ownerService.deleteOwner(id);
            model.addAttribute("successMessage", "Owner successfully deleted.");
            return "sucess"; // or any other page where you want to display the success message
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while deleting the owner.");
            return "error"; // or any other page where you want to display the error message
        }
    }

   


}
