package com.resort.kingfisher.controller;



import com.resort.kingfisher.model.GuestBooking;

import com.resort.kingfisher.model.Room;
import com.resort.kingfisher.service.GuestBookingService;
import com.resort.kingfisher.service.OwnerService;
import com.resort.kingfisher.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GuestBookingController {

    private final GuestBookingService guestBookingService;

    @Autowired
    public GuestBookingController(GuestBookingService guestBookingService) {
        this.guestBookingService = guestBookingService;
    }
    @Autowired
    private RoomService roomService;
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/guest-bookings")
    public String showAllGuestBookings(Model model) {
        List<GuestBooking> guestBookings = guestBookingService.getAllGuestBookings();
        model.addAttribute("guestBookings", guestBookings);
        return "guest-bookings";
    }
    @GetMapping("/create-booking")
    public String showBookingForm(Model model, @RequestParam("ownerId") Long ownerId) {
    	 List<Room> rooms = roomService.getAllRooms();
        
        // Add this logging statement
        System.out.println("Number of rooms: " + rooms.size());

        model.addAttribute("rooms", rooms);
        model.addAttribute("ownerId", ownerId);

        return "guestbook";
    }
    @PostMapping("/submit_booking")
    public String submitBooking(@ModelAttribute GuestBooking guestBooking, Model model) {
        // Check the guest booking
        boolean isAvailable = checkGuestBooking(guestBooking);

        // If the room is available, save the guest booking
        if (isAvailable) {
            // Set the values from the form in the guestBooking object before saving
            guestBooking.setCustomerName(guestBooking.getCustomerName());
            guestBooking.setEmail(guestBooking.getEmail());
            guestBooking.setPhoneNumber(guestBooking.getPhoneNumber());
            guestBooking.setCheckInDate(guestBooking.getCheckInDate());
            guestBooking.setCheckOutDate(guestBooking.getCheckOutDate());
            guestBooking.setRoomName(guestBooking.getRoomName());
            guestBooking.setSpecialRequests(guestBooking.getSpecialRequests());
System.out.println(guestBooking.getCustomerName());
            guestBookingService.saveGuestBooking(guestBooking); // Save the guest booking
            model.addAttribute("successMessage", "Booking successful!"); // Add success message
            return "booking123"; // Redirect to the booking123 page
        } else {
            model.addAttribute("errorMessage", "The selected room is not available for the specified dates. Please choose different dates or a different room."); // Add error message
            return "error"; // Redirect back to the booking form page
        }
    }

    private boolean checkGuestBooking(GuestBooking guestBooking) {
        LocalDate checkInDate = guestBooking.getCheckInDate();
        LocalDate checkOutDate = guestBooking.getCheckOutDate();
        String roomName = guestBooking.getRoomName();

        // Implement your logic to check for availability here
        List<GuestBooking> existingBookings = guestBookingService.getAllGuestBookingsByRoomName(roomName); // Retrieve existing bookings for the specified room name

        for (GuestBooking booking : existingBookings) {
            if (roomName.equals(booking.getRoomName()) &&
                    checkInDate.isBefore(booking.getCheckOutDate()) &&
                    checkOutDate.isAfter(booking.getCheckInDate())) {
                // If the room name matches and the check-in date is before the existing check-out date
                // and the check-out date is after the existing check-in date,
                // there is a conflict in the booking dates
                return false; // Room is not available
            }
        }
        return true; // Room is available
    }
    @PostMapping("/cancelBooking")
    public String cancelBooking(@RequestParam("bookingId") Long bookingId, Model model) {
        GuestBooking booking = guestBookingService.getGuestBookingById(bookingId);

        if (booking != null) {
            boolean isDeleted = guestBookingService.cancelBooking(booking);

            if (isDeleted) {
                model.addAttribute("successMessage", "Booking successfully canceled.");
                return "booking123";
            } else {
                model.addAttribute("successMessage", "Failed to cancel booking.");
                return "booking123";
            }
        } else {
            model.addAttribute("successMessage", "Booking not found.");
            return "booking123";
        }

        // Redirect to the home page or any other appropriate page after canceling the booking
    }
}

    // Other methods

