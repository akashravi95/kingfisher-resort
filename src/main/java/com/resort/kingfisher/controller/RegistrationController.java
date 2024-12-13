package com.resort.kingfisher.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.resort.kingfisher.model.Admin;
import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.model.Owner; // Import the Owner entity
import com.resort.kingfisher.service.AdminService;
import com.resort.kingfisher.service.CustomerService;
import com.resort.kingfisher.service.OwnerService;


@Controller
@CrossOrigin
@RequestMapping("/reg")
public class RegistrationController {
	

    @Autowired
    public CustomerService cusreg;
    @Autowired
	public AdminService admser;

    @Autowired
    public OwnerService ownerService; 

    @PostMapping("/addCustomer")
    public String saveCustomer(@ModelAttribute Customer cust, Model model) {
        try {
            Customer savedCustomer = cusreg.saveCustomer(cust);

            if (savedCustomer != null) {
                model.addAttribute("successMessage", "Customer registration successful");
                return "signup";
            } else {
                model.addAttribute("errorMessage", "Customer registration failed");
                return "home";
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "A customer with the provided data already exists. Please try again with different data.");
            return "error"; // Return to the home page and display the specific error message
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred during customer registration. Please try again later.");
            return "home"; // Return to the home page and display a general error message
        }
    }

    @PostMapping("/addAdmin")
    public Admin saveAdmin(@RequestBody Admin admn) {
        return admser.saveAdmin(admn);
    }
    
   
    @PostMapping("/addOwner")
    public String saveOwner(@ModelAttribute Owner owner, Model model) {
        try {
            Owner savedOwner = ownerService.saveowner(owner); // Fixed the method name and variable name

            if (savedOwner != null) {
                model.addAttribute("successMessage", "Owner registration successful");
                return "booking123"; // Ensure that "booking123" is the correct Thymeleaf template name
            } else {
                model.addAttribute("errorMessage", "Owner registration failed");
                return "error"; // Return to the admin dashboard if owner registration fails
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "An owner with the provided data already exists. Please try again with different data.");
            return "error"; // Return to the error page and display the specific error message
        }
    }



}
