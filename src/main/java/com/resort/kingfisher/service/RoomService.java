package com.resort.kingfisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resort.kingfisher.model.Room;
import com.resort.kingfisher.repo.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Create a new Room
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    // Retrieve all Rooms
    public List<Room> getAllRooms() {
    	
        return roomRepository.findAll();
    }

    // Retrieve a Room by ID
    public Optional<Room> getRoomById(long roomId) {
        return roomRepository.findById(roomId);
    }

    // Update an existing Room
    public void updateRoom(Long id, Room updatedRoom) {
        // Add validation logic if needed
        roomRepository.save(updatedRoom);
    }

    // Delete a Room by ID
    public void deleteRoom(Long id) {
        // Check if the Room with the given ID exists
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            
        } else {
            throw new IllegalArgumentException("Room with ID " + id + " does not exist.");
        }
    }

    public Double getRoomPriceByRoomId(long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            return room.getPricePerNight();
        } else {
            return null; // Room with the specified ID not found
        }
    }


    
}
