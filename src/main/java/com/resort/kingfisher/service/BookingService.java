package com.resort.kingfisher.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.kingfisher.model.Booking;

import com.resort.kingfisher.repo.BookingRepository;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking createBooking(Booking booking) {
        // Implement booking creation logic, e.g., validating dates, etc.
    return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id) {
        // Use the bookingRepository to find a booking by ID
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        // Check if a booking with the given ID exists
        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        } else {
            // Handle the case where the booking is not found (e.g., throw an exception or return null)
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
    }

    public void updateBooking(Long id, Booking updatedBooking) {
        // Check if a booking with the given ID exists
        if (bookingRepository.existsById(id)) {
            updatedBooking.setId(id); // Ensure the ID is set to the correct value
            bookingRepository.save(updatedBooking); // Save the updated booking
        } else {
            // Handle the case where the booking to be updated is not found
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
    }

    public void deleteBooking(Long id) {
        // Check if a booking with the given ID exists
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id); // Delete the booking with the specified ID
        } else {
            // Handle the case where the booking to be deleted is not found
            throw new IllegalArgumentException("Booking with ID " + id + " not found");
        }
    }

	public List<Booking> findByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		 return bookingRepository.findByCustomer_Id(customerId);
	}

	public Booking checkInBooking(Long bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (!booking.isCheckedIn()) {
                booking.setCheckedIn(true);
                return bookingRepository.save(booking);
            } else {
                throw new IllegalStateException("Booking with ID " + bookingId + " is already checked in.");
            }
        } else {
            throw new IllegalArgumentException("Booking with ID " + bookingId + " not found");
        }
    }

    public Booking checkOutBooking(Long bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (booking.isCheckedIn() && !booking.isCheckedOut()) {
                booking.setCheckedOut(true);
                return bookingRepository.save(booking);
            } else if (!booking.isCheckedIn()) {
                throw new IllegalStateException("Booking with ID " + bookingId + " must be checked in before checking out.");
            } else {
                throw new IllegalStateException("Booking with ID " + bookingId + " is already checked out.");
            }
        } else {
            throw new IllegalArgumentException("Booking with ID " + bookingId + " not found");
        }
    }
}
