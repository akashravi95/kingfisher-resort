package com.resort.kingfisher.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.service.CustomerService;


@Controller
public class PasswordResetController {

    @Autowired
    private CustomerService userService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgotpassword";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("email") String email, 
                                @RequestParam("password") String newPassword,
                                Model model) {
        // Look up the user by email
        Customer user = userService.findByEmail(email);

        // Check if the user exists and is eligible for a password reset
        if (user != null) {
        	  user.setPassword(newPassword);
        	    userService.saveCustomer(user);
            model.addAttribute("successMessage", "upadtedpassword");
            return "booking123";
        } else {
            // Redirect to the forgot password page with an error message
            model.addAttribute("errorMessage", "notchange");
            return "error";
        }

    }
}
