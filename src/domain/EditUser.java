package domain;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

@Named
@RequestScoped
public class EditUser {
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String password2;
	
	@Inject
	private UserHolder userHolder;
    
    public String getFirstname() {
    	return firstname;
	}

	public void setFirstname(String firstname) {

		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String updateUser(){
		int passwordHash;
		if (firstname.isEmpty()){
			firstname = userHolder.getCurrentUser().firstName;
		}
		if (lastname.isEmpty()){
			lastname = userHolder.getCurrentUser().lastName;
		}
		if (username.isEmpty()){
			username = userHolder.getCurrentUser().username;
		}
		if (password.isEmpty()){
			passwordHash = userHolder.getCurrentUser().password;
		}
		else{
			passwordHash = password.hashCode();
		}
		
		if(password.equals(password2)){
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.getTransaction().begin();
			Query query = session.createQuery("UPDATE User set firstName =:firstName, lastName =:lastName, username =:username, password =:password" + " WHERE ID = :ID");
			query.setParameter("firstName", firstname);
			query.setParameter("lastName", lastname);
			query.setParameter("username", username);
			query.setParameter("password", passwordHash);
			query.setParameter("ID", userHolder.getCurrentUser().id);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			session.getTransaction().commit();
			return " ";
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
	    	context.addMessage(null, new FacesMessage(
	    			FacesMessage.SEVERITY_ERROR, "Password do not match, Retype your password", null));
	    	return "failed";
		}
	
	}
    
}