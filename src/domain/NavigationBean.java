package domain;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 3792995270068341588L;
	
	/**
	 * Redirect to login page.
	 * @return Login page name.	
	 */
	public String redirectToLogin (){
		return "/login.xhtml?faces-redirect=true";
	}
	
	/**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }
     
    /**
     * Redirect to info page.
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to info page.
     * @return Info page name.
     */
    public String toInfo() {
        return "/info.xhtml";
    }
     
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/secured/admin.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/secured/admin.xhtml";
    }
    
    /**
     * Redirect to welcome page.
     * @return Edit page name.
     */
    public String redirectToEdit() {
        return "/secured/editUser.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to welcome page.
     * @return Edit page name.
     */
    public String toEdit() {
        return "/secured/editUser.xhtml";
    }
     

}
