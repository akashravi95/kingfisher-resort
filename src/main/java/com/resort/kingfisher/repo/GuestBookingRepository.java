package com.resort.kingfisher.repo;

import com.resort.kingfisher.model.GuestBooking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookingRepository extends JpaRepository<GuestBooking, Long> {
	List<GuestBooking> findAllByRoomName(String roomName);
}
