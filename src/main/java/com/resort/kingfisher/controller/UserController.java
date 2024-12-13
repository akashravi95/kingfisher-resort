package com.resort.kingfisher.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.service.CustomerService;



@Controller
@RequestMapping
public class UserController {
@Autowired
public CustomerService customerservice;
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user/user-dashboard";
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        List<Customer> users = customerservice.getAllCustomers(); // Fetch the list of users from your service
        model.addAttribute("users", users); // Add the list of users to the model
        return "admin/users"; // Return the name of the Thymeleaf template (users.html)
    }
    @GetMapping("/editUser/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        Customer user = customerservice.getCustomerById(userId).orElse(new Customer());  // Use 'orElse' to handle the case where the user is not found

        // Make sure the 'user' model attribute has an 'id' field
        model.addAttribute("user", user);
//        model.addAttribute("id", user.getId());
//        model.addAttribute("username", user.getUsername());
//        model.addAttribute("firstName", user.getFirstName());
//        model.addAttribute("lastName", user.getLastName());
//        model.addAttribute("email", user.getEmail());
//        model.addAttribute("phoneNumber", user.getPhoneNumber());
        return "editUserForm";
    }

    

    // Handling the form submission for updating a user
    @PostMapping("/editUser/{userId}")
    public String updateUser(@PathVariable Long userId, @ModelAttribute("user") Customer updatedUser ,Model model) {
        // Perform the update operation in your service
        customerservice.updateCustomer(userId, updatedUser);
        model.addAttribute("successMessage", "updated successfully submitted!");
        return "booking123";
    }

    // Handling the request to delete a user
    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId,Model model) {
        customerservice.deleteCustomer(userId);  // You need to implement this method in your UserService
        model.addAttribute("successMessage", "delete successfully submitted!");
        return "booking123";  // Redirect to the user list or another appropriate page
    }

    }




   
   
   
    


