package com.resort.kingfisher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.resort.kingfisher.model.Feedback;
import com.resort.kingfisher.service.FeedbackService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
	@Autowired
    private FeedbackService feedbackservice;
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response ,Model model  ) {
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        // Prevent caching of the logout page
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies
        List<Feedback> feedbackList = feedbackservice.getAllFeedbacks();
        model.addAttribute("feedbackList", feedbackList);
        return "home"; // Redirect to the login page after logout
    }
}
