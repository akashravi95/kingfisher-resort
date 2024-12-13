package com.resort.kingfisher.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.resort.kingfisher.model.Booking;
import com.resort.kingfisher.service.BookingService;

@Controller
@RequestMapping("/roombookings") // Base URL for room bookings
public class RoomBookingController {
//	  
//
//  private BookingService bookingservice;
//   
//
//    // Handler method to list all room bookings
//    @GetMapping("/list")
//    public String listRoomBookings(Model model) {
//        List<Booking> roomBookings = bookingservice.getAllBookings();
//        model.addAttribute("roomBookings", roomBookings);
//        return "roombooking/list"; // Return the HTML template for listing room bookings
//    }
//
//    // Handler method to display the form for adding a room booking
//    @GetMapping("/add")
//    public String showAddRoomBookingForm(Model model) {
//        Booking roomBooking = new Booking();
//        model.addAttribute("roomBooking", roomBooking);
//        return "roombooking/form"; // Return the HTML template for adding a room booking
//    }
//
//    // Handler method to process the form submission for adding a room booking
//    @PostMapping("/add")
//    public String addRoomBooking(@ModelAttribute("roomBooking") Booking roomBooking) {
//     bookingservice.createBooking(roomBooking);
//        return "redirect:/roombookings/list"; // Redirect to the list of room bookings after adding
//    }
//
//    // Handler method to display the form for updating a room booking
//    @GetMapping("/update/{id}")
//    public String showUpdateRoomBookingForm(@PathVariable Long id, Model model) {
//       Booking roomBooking = bookingservice.getBookingById(id);
//        model.addAttribute("roomBooking", roomBooking);
//        return "roombooking/form"; // Return the HTML template for updating a room booking
//    }
//
//    // Handler method to process the form submission for updating a room booking
//    @PostMapping("/update/{id}")
//    public String updateRoomBooking(@PathVariable Long id, @ModelAttribute("roomBooking") Booking roomBooking) {
//       bookingservice.updateBooking(id, roomBooking);
//        return "redirect:/roombookings/list"; // Redirect to the list of room bookings after updating
//    }
//
//    // Handler method to delete a room booking
//    @GetMapping("/delete/{id}")
//    public String deleteRoomBooking(@PathVariable Long id) {
//       bookingservice.deleteBooking(id);
//        return "redirect:/roombookings/list"; // Redirect to the list of room bookings after deleting
//    }
}
