package domain;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


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
	private int source;
	private List<Flower> flowers;

    
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
	
	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}
	
	public List<Flower> getFlowers() {
		return flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}

	public List<Flower> flowerList(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("FROM Flower flower");
		List<Flower> results = query.list();
		return results;
	}

	public void addWax(){
		ProductDAO productDao = new ProductDAO();
		Product product = new Wax(name, description, price, inStock);
		productDao.save(product);
		System.out.println("Wax product succesfully added");
		
		FacesContext context = FacesContext.getCurrentInstance();
    	context.addMessage(null, new FacesMessage(
    			FacesMessage.SEVERITY_ERROR, "Wax product- " + product.getName() +"succesfully added", null));
	}
	
	 public void addMisc(){
		ProductDAO productDao = new ProductDAO();
		Product product = new Miscellaneous(name, description, expireDate, areaOfApplication, price, inStock);
		productDao.save(product);
		System.out.println("Miscellaneous product succesfully added");
		
		FacesContext context = FacesContext.getCurrentInstance();
    	context.addMessage(null, new FacesMessage(
    			FacesMessage.SEVERITY_ERROR, "Miscellaneous product- " + product.getName() +" succesfully added", null));
	}
	 
	 public void addHoney(){
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.getTransaction().begin();
		 Query query = session.createQuery("FROM Flower WHERE ID ='" + source +"' " );
		 List<Flower> results = query.list();
		 Flower temp = null;
		 for(Flower inStock : results) {
		        temp = inStock;
		    }
//		 System.out.println("im printing" + temp);
		ProductDAO productDao = new ProductDAO();
		Product product = new Honey(name, description, expireDate, temp, price, inStock);
		productDao.save(product);
		System.out.println("Honey product succesfully added");
		
		FacesContext context = FacesContext.getCurrentInstance();
    	context.addMessage(null, new FacesMessage(
    			FacesMessage.SEVERITY_ERROR, "Honey product - " + product.getName() +" succesfully added", null));
	}
	 
	 public void addFlower(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Flower flower = new Flower(name, description);
		session.save(flower);
		session.getTransaction().commit();
		session.close();
		FacesContext context = FacesContext.getCurrentInstance();
    	context.addMessage(null, new FacesMessage(
    			FacesMessage.SEVERITY_ERROR, "Flower succesfully added", null));
	}
    
}