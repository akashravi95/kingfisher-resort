package com.resort.kingfisher.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.resort.kingfisher.model.Booking;
import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.model.Feedback;
import com.resort.kingfisher.model.GuestBooking;
import com.resort.kingfisher.model.Login;
import com.resort.kingfisher.model.Owner;
import com.resort.kingfisher.model.Room;
import com.resort.kingfisher.model.TourPackage;
import com.resort.kingfisher.service.BookingService;
import com.resort.kingfisher.service.CheckLoginService;
import com.resort.kingfisher.service.CustomerService;
import com.resort.kingfisher.service.FeedbackService;
import com.resort.kingfisher.service.GuestBookingService;
import com.resort.kingfisher.service.OwnerService;
import com.resort.kingfisher.service.RoomService;
import com.resort.kingfisher.service.TourPackageService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CheckLoginService checkLoginService;
    
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FeedbackService feedbackservice;
 
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private TourPackageService tourPackageService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private OwnerService ownerservice;
 @Autowired
 private  GuestBookingService guestBookingService;
    
    private static final String ROLE_ADMIN = "1";
    private static final String ROLE_CUSTOMER = "2";
    private static final String ROLE_OWNER = "3";

    @PostMapping
    public String performLogin(Login log, Model model, HttpSession session) {
        try {
            Map<String, Long> loginResult = checkLoginService.checkLogin(log);

            if (loginResult.get("STATUS").equals(200L)) {
                String role = String.valueOf(loginResult.get("ROLE"));
                session.setAttribute("USER_ROLE", role);

                if (ROLE_ADMIN.equals(role)) {
                	List<Customer> customers = customerService.getAllCustomers();
                    List<Owner> owners = ownerservice.getAllOwners();
                    // Add the data to the model so it can be accessed in the Thymeleaf template
                    model.addAttribute("customers", customers);
                    model.addAttribute("owners", owners);
                    // Return the name of the Thymeleaf template for the admin dashboard
                    return "admin/admin-dashboard";
                } else if (ROLE_CUSTOMER.equals(role)) {
                	
                    // Retrieve the user by username
                    Customer customer = customerService.findCustomerByUsername(log.getUsername());

                    // Print username and ID to the console
                    System.out.println("Logged in user: " + customer.getUsername() + " (ID: " + customer.getId() + ")");
                    model.addAttribute("user", customer);
                    List<Room> rooms = roomService.getAllRooms();
//                    for (Room room : rooms) {
//						System.out.println(room.getStatus());
//					}
                    model.addAttribute("rooms", rooms);
                    // Redirect to the customer page
                    return "user/user-dashboard";
                } else if (ROLE_OWNER.equals(role)) {
                    // Add the homestays to the model to make them accessible in the Thymeleaf template
                    
                    List<Room> rooms = roomService.getAllRooms();
                    
                    model.addAttribute("rooms", rooms);
                    
                    List<TourPackage> tourPackages = tourPackageService.getAllTourPackages();
                    model.addAttribute("tourpackages", tourPackages);
                    
                    List<Booking> roomBookings = bookingService.getAllBookings();
                    // Add the room bookings to the model
                    model.addAttribute("roomBookings", roomBookings);

                    List<Customer> users = customerService.getAllCustomers(); // Fetch the list of users from your service
                    model.addAttribute("users", users);
                    List<Owner> owner = ownerservice.getAllOwners();
                    model.addAttribute("owners", owner);

                   
                    model.addAttribute("owner", owner.get(0));
                    
                    List<Feedback> feedbackList = feedbackservice.getAllFeedbacks();
                    model.addAttribute("feedbackList", feedbackList);
                    List<GuestBooking> guestBookings = guestBookingService.getAllGuestBookings(); // Replace with the appropriate method to fetch all guest bookings
                    model.addAttribute("guestBookings", guestBookings);

                    return "owner/owner-dashboard";
                }
            } else {
                model.addAttribute("error", "Invalid credentials");
                return "login"; // Return to the login page with an error message
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred during login.");
            return "login"; // Return to the login page with an error message
        }
        
        return "login"; // Return to the login page if something unexpected happens
    }
}
