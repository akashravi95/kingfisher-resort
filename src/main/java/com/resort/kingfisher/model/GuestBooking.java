package com.resort.kingfisher.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "tbl_Guest_bookings")
public class GuestBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    private String email;
    private String phoneNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String roomName;
    private String specialRequests;
    private int adults;
    private int children;

    // Include constructors, getters, and setters as needed

    // Other code in the class


    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getSpecialRequests() {
		return specialRequests;
	}
	public void setSpecialRequests(String specialRequests) {
		this.specialRequests = specialRequests;
	}
	
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public GuestBooking(String customerName, String email, String phoneNumber, LocalDate checkInDate,
			LocalDate checkOutDate, String roomName, String specialRequests, int adults, int children) {
		super();
		this.customerName = customerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomName = roomName;
		this.specialRequests = specialRequests;
		this.adults = adults;
		this.children = children;
	}
	public GuestBooking() {
		
	}
	@Override
	public String toString() {
		return "GuestBooking [id=" + id + ", customerName=" + customerName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", roomName="
				+ roomName + ", specialRequests=" + specialRequests + ", adults=" + adults + ", children=" + children
				+ "]";
	}

	
	
    
}
