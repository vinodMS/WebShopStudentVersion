package domain;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 2696833672905609067L;
	private String username;
    private String password;
    private boolean loggedIn;
    private boolean loggedInAdmin;
    
    @Inject
    private UserHolder userHolder;
    
    @Inject
    private NavigationBean navigationBean;
    
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
	 public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public boolean isLoggedInAdmin() {
		return loggedInAdmin;
	}
	public void setLoggedInAdmin(boolean loggedInAdmin) {
		this.loggedInAdmin = loggedInAdmin;
	}
	public String login() {
	    	UserDAO userDao = new UserDAO();
	    	User authUser = userDao.authenticate(username, password.hashCode()); // converting password to hashcode, password in db are saved as hash
	    	if (authUser == null){
	    		FacesContext context = FacesContext.getCurrentInstance();
	        	context.addMessage(null, new FacesMessage(
	        			FacesMessage.SEVERITY_ERROR, "Login failed", null));
	        	return "failed";
	    	}else{
	    		
	    		if(authUser.getAdmin() == 1){ // checking if it's admin user or not;
	    			setLoggedInAdmin(true);
	    		}
	  			
	    		setLoggedIn(true);
	    		userHolder.setCurrentUser(authUser); // setting current user in session
//	    		return navigationBean.redirectToEdit();
	    		return "loggedIn";
	    	}
	   }
	 
	 public String doLogout() {
	        setLoggedIn(false);
	        setLoggedInAdmin(false); 
	        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_INFO);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	   
	        return navigationBean.toLogin();
	    }
}