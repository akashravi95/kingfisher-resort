package com.resort.kingfisher.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_admin")
public class Admin {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique = true)
    private String username;
    private String password;
    private String role;

    @OneToOne(mappedBy = "admin")
    private Login login;

  

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Feedback> feedbackQuestions;



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



	public List<Feedback> getFeedbackQuestions() {
		return feedbackQuestions;
	}



	public void setFeedbackQuestions(List<Feedback> feedbackQuestions) {
		this.feedbackQuestions = feedbackQuestions;
	}



	public Admin(String username, String password, String role, Login login, List<Feedback> feedbackQuestions) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.login = login;
		this.feedbackQuestions = feedbackQuestions;
	}



	public Admin() {
		
	}



	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", login="
				+ login + ", feedbackQuestions=" + feedbackQuestions + "]";
	}
    
}