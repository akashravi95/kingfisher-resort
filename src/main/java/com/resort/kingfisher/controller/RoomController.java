package com.resort.kingfisher.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.resort.kingfisher.model.Owner;
import com.resort.kingfisher.model.Room;
import com.resort.kingfisher.service.BookingService;
import com.resort.kingfisher.service.CheckLoginService;
import com.resort.kingfisher.service.CustomerService;
import com.resort.kingfisher.service.FeedbackService;
import com.resort.kingfisher.service.OwnerService;
import com.resort.kingfisher.service.RoomService;
import com.resort.kingfisher.service.TourPackageService;

import java.util.List;
import java.util.Optional;




	@Controller
	@RequestMapping
	public class RoomController {

	    private final RoomService roomService;
	    private final OwnerService ownerService; // Assuming you have an OwnerService

	    @Autowired
	    public RoomController(RoomService roomService, OwnerService ownerService) {
	        this.roomService = roomService;
	        this.ownerService = ownerService;
	    }
	    @Autowired
	    private CheckLoginService checkLoginService;
	    
	    @Autowired
	    private CustomerService customerService;
	    @Autowired
	    private FeedbackService feedbackservice;
	 
	   
	    
	    @Autowired
	    private TourPackageService tourPackageService;
	    
	    @Autowired
	    private BookingService bookingService;
	    
	    @GetMapping("/create-room")
	    public String showCreateRoomForm(@RequestParam(name = "ownerId", required = false) Long ownerId, Model model) {
	        if (ownerId != null) {
	            // Fetch the owner details by ownerId (you can use your OwnerService)
	            Owner owner = ownerService.getOwnerById(ownerId);
	            model.addAttribute("owner", owner);
	        }

	        // Fetch all owners and add them to the model
	        List<Owner> owners = ownerService.getAllOwners();
	        model.addAttribute("owners", owners);

	        // Create an empty Room object for the form
	        model.addAttribute("room", new Room());

	        return "addroom";
	    }



	    @PostMapping("/rooms")
	    public String createRoom(@ModelAttribute Room room ,Model model) {
	        // Save the room to the database using your RoomService
	        

	     
	        if (room.getOwner() != null) {
	            Long ownerId = room.getOwner().getId();
	            System.out.println("Owner ID: " + ownerId);
	            
	        } else {
	            System.out.println("Owner is null");
	        }
	        
	        roomService.saveRoom(room);
	        model.addAttribute("successMessage", "successfully submitted!");
	        return "booking123"; // Redirect to a list of rooms or another page
	    }


	    @GetMapping("/updateroom/{id}")
	    public String showUpdateRoomForm(@PathVariable Long id, Model model) {
	        Optional<Room> optionalRoom = roomService.getRoomById(id);
	        if (optionalRoom.isPresent()) {
	            Room room = optionalRoom.get();
	            model.addAttribute("room", room);
	            return "updateroom"; // Make sure the Thymeleaf template name is correct
	        } else {
	            // Handle the case where the room is not found
	            return "error"; // You can create an error template
	        }
	    }

	    // Handle the submission of the updated room form
	    @PostMapping("/updateroom/{id}")
	    public String updateRoom(@PathVariable Long id, @ModelAttribute Room updatedRoom ,Model model ) {
	        roomService.updateRoom(id, updatedRoom);
	        model.addAttribute("successMessage", "successfully updated!");
	        return "booking123"; // Replace with the appropriate redirect link
	    }
	  

    // Delete a room
	    @GetMapping("/deleteRoom/{id}")
	    public String deleteRoom(@PathVariable Long id, Model model) {
	        roomService.deleteRoom(id);
	        model.addAttribute("successMessage", "delete successfully submitted!");
	        return "booking123";
	    }
}
