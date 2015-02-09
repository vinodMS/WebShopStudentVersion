package domain;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class RegisterRequest {
	private String firstname;
	private String lastname;
	private String username;
    private String password;
    
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String saveUser(){
		UserDAO userDao = new UserDAO();
//		Integer userId= userDao.getId();
		System.out.println(firstname);
		User user = new User(firstname, lastname, username,password);
		userDao.save(user);
		System.out.println("User successfully saved.");
		return "output";
	}
    
    
}