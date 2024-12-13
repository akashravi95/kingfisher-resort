package com.resort.kingfisher.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double pricePerNight;
    private String fileUrl;   
   

    // Define additional fields and getters/setters as needed
    @OneToMany(mappedBy = "room") // One room can have multiple bookings
    private List<Booking> bookings;
   
    @ManyToOne
    @JoinColumn(name = "owner_id") // Specify the foreign key column in the rooms table
    private Owner owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Room(String name, String description, double pricePerNight, String fileUrl, List<Booking> bookings,
			Owner owner) {
		super();
		this.name = name;
		this.description = description;
		this.pricePerNight = pricePerNight;
		this.fileUrl = fileUrl;
		this.bookings = bookings;
		this.owner = owner;
	}

	public Room() {

	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", description=" + description + ", pricePerNight=" + pricePerNight
				+ ", fileUrl=" + fileUrl + ", bookings=" + bookings + ", owner=" + owner + "]";
	}
    
    }