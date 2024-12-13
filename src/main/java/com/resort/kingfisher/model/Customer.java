package com.resort.kingfisher.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
@Entity
@Table(name = "tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private String phoneNumber;
    private String role;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Login login;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Feedback> feedback;

    @OneToMany(mappedBy = "customerSender", cascade = CascadeType.ALL)
    private List<ChatMessage> sentMessages;

    @OneToMany(mappedBy = "customerReceiver", cascade = CascadeType.ALL)
    private List<ChatMessage> receivedMessages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public List<ChatMessage> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<ChatMessage> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<ChatMessage> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<ChatMessage> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Customer(String username, String password, String firstName, String lastName, @Email String email,
			String phoneNumber, String role, Login login, List<Booking> bookings, List<Feedback> feedback,
			List<ChatMessage> sentMessages, List<ChatMessage> receivedMessages) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.login = login;
		this.bookings = bookings;
		this.feedback = feedback;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
	}

	public Customer() {
		
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role
				+ ", login=" + login + ", bookings=" + bookings + ", feedback=" + feedback + ", sentMessages="
				+ sentMessages + ", receivedMessages=" + receivedMessages + "]";
	}


	

}