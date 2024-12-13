package com.resort.kingfisher.model;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "tbl_owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Login login;


    @OneToMany(mappedBy = "owner")
    private List<Room> rooms;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<TourPackage> tourPackages;

    @OneToMany(mappedBy = "ownerSender", cascade = CascadeType.ALL)
    private List<ChatMessage> sentMessages;

    @OneToMany(mappedBy = "ownerReceiver", cascade = CascadeType.ALL)
    private List<ChatMessage> receivedMessages;

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

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<TourPackage> getTourPackages() {
		return tourPackages;
	}

	public void setTourPackages(List<TourPackage> tourPackages) {
		this.tourPackages = tourPackages;
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

	public Owner(String name, String username, String password, String role, Login login, List<Room> rooms,
			List<TourPackage> tourPackages, List<ChatMessage> sentMessages, List<ChatMessage> receivedMessages) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.login = login;
		this.rooms = rooms;
		this.tourPackages = tourPackages;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
	}

	public Owner() {
		
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", role="
				+ role + ", login=" + login + ", rooms=" + rooms + ", tourPackages=" + tourPackages + ", sentMessages="
				+ sentMessages + ", receivedMessages=" + receivedMessages + "]";
	}

}
