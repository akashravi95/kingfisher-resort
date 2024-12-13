package com.resort.kingfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.resort.kingfisher.model.Feedback;
import com.resort.kingfisher.model.Owner;
import com.resort.kingfisher.model.Room;

import com.resort.kingfisher.service.FeedbackService;
import com.resort.kingfisher.service.RoomService;

@Controller
@RequestMapping
public class HomeController   {
	@Autowired
	 private  FeedbackService feedbackservice;
	


   @Autowired
   private RoomService roomService;
   
    @GetMapping("signup")
    public String showsignupPage() {
        return "signup";
    }
    @GetMapping("loginpage")
    public String loginpage() {
        return "loginpage";
    }
    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Feedback> feedbackList = feedbackservice.getAllFeedbacks();
        model.addAttribute("feedbackList", feedbackList);
        List<Room> rooms = roomService.getAllRooms(); // Fetch rooms from the database
        model.addAttribute("rooms", rooms);
        // Add any other data you want to pass to the home page
        return "home";
   
    }
   
    @GetMapping("ownersignup")
    public String showOwnerForm(Model model) {
        Owner owner = new Owner(); // Create a new Owner object
        model.addAttribute("owner", owner); // Add the owner object to the model
        return "admin/ownersignup"; // Return the Thymeleaf view name
    }
  
    
   
}