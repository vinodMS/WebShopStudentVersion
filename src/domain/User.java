package domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private static int currentId =1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	int id;
	@Column(name="firstName")
	String firstName;
	@Column(name="lastName")
	String lastName;
	@Column(name="username")
	String username;
	@Column(name="password")
	String password;
	
	public User(String firstName, String lastName, String username) {
		super();
		this.id = currentId++; // Simple way of getting an unique id. To be deferred to the DBMS later.
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}

	public User(String firstName, String lastName, String username, String password) {
		super();
		this.id = id; // Simple way of getting an unique id. To be deferred to the DBMS later.
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(int id){
		this.id = (int) currentId++;
	}
	
	public int getId() {
		return id;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	@Override
	public String toString() {
		return "Person " + firstName + " " + lastName + " has username " + username;
	}
	
	public boolean authorize(String username, String password) {
		if ((this.username.equals(username) && (this.password.equals(password)))) {
			return true;
		}
		else {
			return false;
		}
	}
}
