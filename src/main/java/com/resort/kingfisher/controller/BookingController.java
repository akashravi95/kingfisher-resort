package com.resort.kingfisher.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.resort.kingfisher.model.Booking;
import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.model.Room;
import com.resort.kingfisher.service.BookingService;
import com.resort.kingfisher.service.CustomerService;
import com.resort.kingfisher.service.RoomService;

@Controller // Use @Controller instead of @RestController for handling HTML form submissions
@RequestMapping("/book")
public class BookingController {
	  @Autowired
	    private RoomService roomService;

	    @Autowired
	    private BookingService bookingservice;

	    @Autowired
	    private CustomerService customerService;
	    
	    private boolean isRoomAvailable(Long roomId, Date checkInDate, Date checkOutDate) {
	        // Retrieve the room by its ID
	        Optional<Room> roomOptional = roomService.getRoomById(roomId);

	        if (roomOptional.isEmpty()) {
	            // Handle the case where the room does not exist
	            return false;
	        }

	        Room room = roomOptional.get();

	        // Get the list of bookings for the specified room
	        List<Booking> bookings = room.getBookings();

	        // Use Java Streams to check if there are any conflicting bookings
	        boolean isAvailable = bookings.stream().noneMatch(booking ->
	                !(checkOutDate.before(booking.getCheckInDate()) || checkInDate.after(booking.getCheckOutDate())));

	        return isAvailable;
	    }

	    @GetMapping("/bookNow")
	    public String bookNow(
	            @RequestParam("roomId") long roomId,
	            @RequestParam("userId") long userId,
	            @RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
	            @RequestParam("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate,
	            Model model
	    ) {
	        Optional<Room> roomOptional = roomService.getRoomById(roomId);
	        Optional<Customer> customerOptional = customerService.getCustomerById(userId);

	        if (roomOptional.isPresent() && customerOptional.isPresent()) {
	            Room room = roomOptional.get();
	            Customer customer = customerOptional.get();

	            // Fetch the room's price from the database based on the roomId
	            Double roomPrice = roomService.getRoomPriceByRoomId(roomId);

	            // Add room availability check logic here for the specified date range
	            if (isRoomAvailable(roomId, checkInDate, checkOutDate)) {
	                Booking booking = new Booking();
	                booking.setRoom(room);
	                booking.setCustomer(customer);
	                booking.setCheckInDate(checkInDate);
	                booking.setCheckOutDate(checkOutDate);
	                
	                // Pass the room price to the view
	                model.addAttribute("roomPrice", roomPrice); // Replace roomPrice with the actual variable containing the price

	                
	                model.addAttribute("room", room);
	                model.addAttribute("customer", customer);
	                model.addAttribute("booking", booking);

	                return "booking"; // This should match the name of your Thymeleaf template (booking.html)
	            } else {
	                // Handle the case where the room is not available for the specified date range
	                model.addAttribute("errorMessage", "Room is not available for the selected dates.");
	                return "error"; // Create an "error.html" template for this purpose
	            }
	        } else {
	            // Handle the case where either the room or customer was not found
	            // You can redirect to an error page or return an error message
	            return "error"; // Create an "error.html" template for this purpose
	        }
	    }

	    @PostMapping("/save")
	    public String saveBooking(@ModelAttribute("booking") Booking booking, Model model) {
	        if (!isRoomAvailable(booking.getRoom().getId(), booking.getCheckInDate(), booking.getCheckOutDate())) {
	            model.addAttribute("errorMessage", "Room is not available for the selected dates.");
	            return "error";
	        }

	        if (booking.getCheckInDate().after(booking.getCheckOutDate())) {
	            model.addAttribute("errorMessage", "Invalid date range. Check-Out date should be after Check-In date.");
	            return "error";
	        }

	        long days = calculateNumberOfDays(booking.getCheckInDate(), booking.getCheckOutDate());
	        if (days >= 0) {
	            double roomPrice = roomService.getRoomPriceByRoomId(booking.getRoom().getId());
	            double totalPrice = roomPrice * days;
	            booking.setAmount(totalPrice);

	            bookingservice.createBooking(booking);

	            model.addAttribute("successMessage", "Booking successfully submitted!");
	            return "booking123";
	        } else {
	            model.addAttribute("errorMessage", "Invalid date range. Check-Out date should be after Check-In date.");
	            return "error";
	        }
	    }

    // Calculate the number of days between two dates
	    private long calculateNumberOfDays(Date checkInDate, Date checkOutDate) {
	        long millisecondsPerDay = 24 * 60 * 60 * 1000; // Milliseconds in a day
	        long numberOfDays = (checkOutDate.getTime() - checkInDate.getTime()) / millisecondsPerDay;

	        // If check-in and check-out are on the same day, add 1 to the total
	        if (numberOfDays == 0) {
	            numberOfDays = 1;
	        }
	        
	        return numberOfDays;
	    }



    @GetMapping("/{customerId}")
    public String viewCustomerProfile(@PathVariable Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId).orElse(null);
        if (customer != null) {
            model.addAttribute("customer", customer);
            List<Booking> bookings = bookingservice.findByCustomerId(customerId);
            model.addAttribute("bookings", bookings);
        }
        return "user/profile";
    }
    @PostMapping("/checkin/{id}")
    public String checkInBooking(@PathVariable Long id, Model model) {
        try {
            Booking booking = bookingservice.checkInBooking(id);
            model.addAttribute("successMessage", "Check-in successful.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "booking123"; // Redirect to the booking page
    }

    @PostMapping("/checkout/{id}")
    public String checkOutBooking(@PathVariable Long id, Model model) {
        try {
            Booking booking = bookingservice.checkOutBooking(id);
            model.addAttribute("successMessage", "Check-out successful.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "booking123"; // Redirect to the booking page
    }

    @GetMapping("/updateBooking/{id}")
    public String showUpdateBookingForm(@PathVariable Long id, Model model) {
        // Implement logic to fetch the booking details by ID and prepare the form model
        // You can fetch the booking by ID and set it in the model
        Booking booking = bookingservice.getBookingById(id);
        
        // Assuming you also have a customer associated with the booking
        Customer customer = booking.getCustomer();
        
        model.addAttribute("booking", booking);
        model.addAttribute("customer", customer); // Add the customer object to the model
        
        return "booking"; // Return the name of the Thymeleaf template for updating bookings
    }


   @PostMapping("/updateBooking/{id}")
    public String updateBooking(@PathVariable Long id, @ModelAttribute("booking") Booking updatedBooking, Model model) {
        Booking existingBooking = bookingservice.getBookingById(id);
        if (existingBooking == null) {
            model.addAttribute("errorMessage", "Booking not found");
            return "error";
        }

        // Update the existing booking with the new details
        bookingservice.updateBooking(id, updatedBooking);

        model.addAttribute("successMessage", "Booking updated successfully!");
        return "booking123";
    }

    @GetMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable Long id, Model model) {
        // Implement logic to delete the booking by ID
        bookingservice.deleteBooking(id);
        model.addAttribute("successMessage", "Booking deleted successfully!");
        return "booking123";
    }

    @GetMapping("/extend/{id}")
    public String showExtendForm(@PathVariable Long id, Model model) {
        Booking roomBooking = bookingservice.getBookingById(id);

        if (roomBooking == null) {
            return "redirect:/"; // Handle error or redirect as needed.
        }

        // Include the booking details in the model
        model.addAttribute("roomBooking", roomBooking);

        // Assuming you have a method to get the amount from the Booking class.
  

        return "extend-booking";
    }

    @PostMapping("/extend/{id}")
    public String extendBooking(@PathVariable Long id, @ModelAttribute("roomBooking") Booking roomBooking, Model model) {
        System.out.println(id);

        // Retrieve customer and room IDs from hidden fields
        Long roomId = roomBooking.getRoom().getId();

        // Retrieve the room object based on its ID
        Room room = roomService.getRoomById(roomId).orElse(null);

        // Ensure that the retrieved room is not null
        if (room == null) {
            model.addAttribute("errorMessage", "The room information is missing.");
            return "error"; // You may want to have a specific error page for extending bookings.
        }

        // Get the existing booking or retrieve the customer ID from your logic
        Booking existingBooking = bookingservice.getBookingById(id);
        Long customerId = existingBooking.getCustomer().getId();

        // Ensure that the `roomBooking` object has a `Customer` and set the customer's ID
        if (roomBooking.getCustomer() == null) {
            Customer customer = new Customer();
            // Set other customer attributes as needed
            roomBooking.setCustomer(customer);
        }
        roomBooking.getCustomer().setId(customerId);

        // Check if the room is available for the new checkout date
        if (!isRoomAvailableByCheckoutDate(room.getId(), roomBooking.getCheckOutDate())) {
            model.addAttribute("errorMessage", "Room is not available for the selected checkout date.");
            return "error"; // You may want to have a specific error page for extending bookings.
        }

     // Check if the Check-Out date is changed
        if (!existingBooking.getCheckOutDate().equals(roomBooking.getCheckOutDate())) {
            // Calculate the number of days for the extension
            long days = calculateNumberOfDays(roomBooking.getCheckInDate(), roomBooking.getCheckOutDate());
            if (days >= 0) {
                double roomPrice = roomService.getRoomPriceByRoomId(room.getId());
                double totalPrice = roomPrice * days;
                roomBooking.setAmount(totalPrice);

                // Update the existing booking by its ID
                bookingservice.updateBooking(id, roomBooking);

                model.addAttribute("successMessage", "Booking successfully extended!");
                return "booking123"; // The page you want to show for a successful extension
            } else {
                model.addAttribute("errorMessage", "Invalid date range. Check-Out date should be after the current Check-In date.");
                return "error"; // You may want to have a specific error page for extending bookings.
            }
        } else {
            // Check if the Check-In date is changed
            if (!existingBooking.getCheckInDate().equals(roomBooking.getCheckInDate())) {
                // Calculate the number of days for the extension
                long days = calculateNumberOfDays(roomBooking.getCheckInDate(), roomBooking.getCheckOutDate());
                if (days >= 0) {
                    double roomPrice = roomService.getRoomPriceByRoomId(room.getId());
                    double totalPrice = roomPrice * days;
                    roomBooking.setAmount(totalPrice);

                    // Update the existing booking by its ID
                    bookingservice.updateBooking(id, roomBooking);

                    model.addAttribute("successMessage", "Booking successfully extended!");
                    return "booking123"; // The page you want to show for a successful extension
                } else {
                    model.addAttribute("errorMessage", "Invalid date range. Check-Out date should be after the current Check-In date.");
                    return "error"; // You may want to have a specific error page for extending bookings.
                }
            } else {
                model.addAttribute("errorMessage", "Checkout date is the same as the current booking.");
                return "error"; // You may want to have a specific error page for extending bookings.
            }
        }
    }

	private boolean isRoomAvailableByCheckoutDate(Long id, Date checkOutDate) {
		// TODO Auto-generated method stub
		return true;
	}
	@GetMapping("/changeRoom/{id}")
	public String showChangeRoomForm(@PathVariable Long id, Model model) {
	    // Load the booking based on the given ID
	    Booking booking = bookingservice.getBookingById(id);
	    
	    // You can load a list of available rooms for the user to choose from
	    List<Room> availableRooms = roomService.getAllRooms();
	    
	    model.addAttribute("booking", booking);
	    model.addAttribute("availableRooms", availableRooms);
	    
	    return "changeRoomForm"; // Create an HTML form for changing the room
	}

	@PostMapping("/changeRoom/{id}")
	public String changeRoom(@PathVariable Long id, @RequestParam Long newRoomId, @RequestParam long adults, @RequestParam long children, Model model) {
	    Booking booking = bookingservice.getBookingById(id);
	    Room newRoom = roomService.getRoomById(newRoomId).orElse(null);

	    if (newRoom != null) {
	        // Check if the new room is available for the selected date range
	        if (isRoomAvailable(newRoom.getId(), booking.getCheckInDate(), booking.getCheckOutDate())) {
	            // Calculate the new amount based on the new room's price and duration
	            double newRoomPrice = roomService.getRoomPriceByRoomId(newRoom.getId());
	            long days = calculateNumberOfDays(booking.getCheckInDate(), booking.getCheckOutDate());
	            double newAmount = newRoomPrice * days;

	            // Update the booking with the new room and amount
	            booking.setRoom(newRoom);
	            booking.setAmount(newAmount);
	            booking.setAdult(adults); // Set the number of adults (corrected from "setAdult")
	            booking.setChild(children); // Set the number of children (corrected from "setChild")

	            bookingservice.updateBooking(id, booking);
	            model.addAttribute("successMessage", "Room changed successfully");

	            return "booking123"; // Redirect to the bookings list page or another appropriate page
	        } else {
	            model.addAttribute("errorMessage", "New room is not available for the selected date range.");
	            return "error"; // You may want to have a specific error page for this case
	        }
	    } else {
	        model.addAttribute("errorMessage", "New room not found.");
	        return "error"; // You may want to have a specific error page for changing the room
	    }
	}


    }



