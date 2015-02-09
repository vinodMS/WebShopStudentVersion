package domain;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginRequest {
    private String username;
    private String password;
    @Inject
    private UserHolder userHolder;
    
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	 public String login() {
	    	UserDAO userDao = new UserDAO();
	    	User authUser = userDao.authenticate(username, password);
	    	if (authUser == null){
	    		FacesContext context = FacesContext.getCurrentInstance();
	        	context.addMessage(null, new FacesMessage(
	        			FacesMessage.SEVERITY_ERROR, "Login failed", null));
	        	return "failed";
	    	}else{
	    		System.out.println("success");
	    		userHolder.setCurrentUser(authUser);
	    		System.out.println("The current user is" + userHolder.getCurrentUser());
	    		return "loggedIn";
	    	}
	   }
}