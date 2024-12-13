package com.resort.kingfisher.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "tlb_bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long adult;
    private long child;
    @ManyToOne // Many bookings can be made by one customer
    @JoinColumn(name = "customer_id") // Foreign key column in the bookings table
    private Customer customer; // Represents the customer who made the booking

    @ManyToOne // Each booking is for one room
    @JoinColumn(name = "room_id") // Foreign key column in the bookings table
    private Room room; // Represents the room that was booked
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specify the expected date format
    private Date checkInDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specify the expected date format
    private Date checkOutDate;
 
    private boolean checkedIn;
    private boolean checkedOut;
    @Column(name = "amount", nullable = true) // Or use @Column(nullable = false) if it should not be nullable
    private Double amount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAdult() {
		return adult;
	}
	public void setAdult(long adult) {
		this.adult = adult;
	}
	public long getChild() {
		return child;
	}
	public void setChild(long child) {
		this.child = child;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public boolean isCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public boolean isCheckedOut() {
		return checkedOut;
	}
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Booking(long adult, long child, Customer customer, Room room, Date checkInDate, Date checkOutDate,
			boolean checkedIn, boolean checkedOut, Double amount) {
		super();
		this.adult = adult;
		this.child = child;
		this.customer = customer;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.checkedIn = checkedIn;
		this.checkedOut = checkedOut;
		this.amount = amount;
	}
	public Booking() {
	
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", adult=" + adult + ", child=" + child + ", customer=" + customer + ", room="
				+ room + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", checkedIn=" + checkedIn
				+ ", checkedOut=" + checkedOut + ", amount=" + amount + "]";
	}
    
	
}