package com.resort.kingfisher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_tourpackages")
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    // Define additional fields and getters/setters as needed

    @ManyToOne
    @JoinColumn(name = "owner_id")
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public TourPackage(String name, String description, double price, Owner owner) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.owner = owner;
	}

	public TourPackage() {
		
	}

	@Override
	public String toString() {
		return "TourPackage [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", owner=" + owner + "]";
	}

    // Constructors, getters, setters, and other methods
}