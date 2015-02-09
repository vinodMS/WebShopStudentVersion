package domain;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.Date;


@Named
@SessionScoped
public class addProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private double price;
	private int inStock;
	private Date expireDate;
	private String areaOfApplication;
	Session session = HibernateUtil.getSessionFactory().openSession();
//    @Inject
//    private UserHolder productHolder;
    
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
	
    public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public Date getExpireDate() {
		return expireDate;
	}
    
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getAreaOfApplication() {
		return areaOfApplication;
	}
	
	public void setAreaOfApplication(String areaOfApplication) {
		this.areaOfApplication = areaOfApplication;
	}
	
	public void addWax(){
		ProductDAO productDao = new ProductDAO();
		Product product = new Wax(name, description, price, inStock);
		productDao.save(product);
		System.out.println("Wax product succesfully added");
		
		FacesContext context = FacesContext.getCurrentInstance();
    	context.addMessage(null, new FacesMessage(
    			FacesMessage.SEVERITY_ERROR, "Wax product succesfully added", null));
	}
	
	 public void addMisc(){
		ProductDAO productDao = new ProductDAO();
		Product product = new Miscellaneous(name, description, expireDate, areaOfApplication, price, inStock);
		productDao.save(product);
		System.out.println("Wax product succesfully added");
		
		FacesContext context = FacesContext.getCurrentInstance();
    	context.addMessage(null, new FacesMessage(
    			FacesMessage.SEVERITY_ERROR, "Wax product succesfully added", null));
	}
    
    
}