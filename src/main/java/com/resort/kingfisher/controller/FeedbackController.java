package com.resort.kingfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.model.Feedback;
import com.resort.kingfisher.model.Room;
import com.resort.kingfisher.service.CustomerService;
import com.resort.kingfisher.service.FeedbackService;
import com.resort.kingfisher.service.RoomService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	 @Autowired
	    private  RoomService roomservice;
	 @Autowired
    private FeedbackService feedbackservice;
   
 
@Autowired
private CustomerService customerservice;


    @PostMapping
    public String submitFeedback(Feedback feedback, @RequestParam("customerId") Long customerId ,Model model) {
        Customer customer = customerservice.getCustomerById(customerId).get(); // Retrieve the customer
        feedback.setCustomer(customer); // Set the customer in the Feedback object

        feedbackservice.saveFeedback(feedback); // Save the feedback

        model.addAttribute("successMessage", "sucess");

        return "booking123";
    }
    @GetMapping
    public String showHomePage(Model model) {
        List<Feedback> feedbackList = feedbackservice.getAllFeedbacks();
        model.addAttribute("feedbackList", feedbackList);

        List<Room> rooms = roomservice.getAllRooms(); // Fetch rooms from the database
        model.addAttribute("rooms", rooms);

        // Add any other data you want to pass to the home page

        return "owner/owner-dashboard"; // Assuming "home" is the name of your Thymeleaf template
    }
    @DeleteMapping("/deleteFeedback/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        try {
            feedbackservice.deleteFeedback(feedbackId);
            return ResponseEntity.ok("Feedback deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting feedback");
        }
    }

    }
