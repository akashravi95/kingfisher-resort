package com.resort.kingfisher.service;
import com.resort.kingfisher.model.GuestBooking;
import com.resort.kingfisher.repo.GuestBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestBookingService {

    private final GuestBookingRepository guestBookingRepository;

    @Autowired
    public GuestBookingService(GuestBookingRepository guestBookingRepository) {
        this.guestBookingRepository = guestBookingRepository;
    }

    public List<GuestBooking> getAllGuestBookings() {
        return guestBookingRepository.findAll();
    }

    public void saveGuestBooking(GuestBooking guestBooking) {
        guestBookingRepository.save(guestBooking);
    }

    public List<GuestBooking> getAllGuestBookingsByRoomName(String roomName) {
        return guestBookingRepository.findAllByRoomName(roomName);
    }


    public boolean cancelBooking(GuestBooking booking) {
        if (booking != null) {
            guestBookingRepository.delete(booking);
            return true;
        } else {
            return false;
        }
    }

	public GuestBooking getGuestBookingById(Long bookingId) {
		  Optional<GuestBooking> optionalBooking = guestBookingRepository.findById(bookingId);
	        return optionalBooking.orElse(null);
	}
}
