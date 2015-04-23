package domain;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
//import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Named
@RequestScoped
public class editProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private double price;
	private int inStock;
	private Date expireDate;
	private String areaOfApplication;
	private int source;
	private List<Flower> flowers;

    @Inject
    ProductHolder ph;
    
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
	
	public String editProducts(Product product){
		ph.setCurrentProduct(product);
		
		if(product.productType.equals("Honey")){
			return "editHoney";
		}
		return "null";
		
	}
	
	
	public List<Flower> flowerList(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("FROM Flower flower");
		@SuppressWarnings("unchecked")
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
	 
	 public void updateHoney(){
		 	name = ph.getCurrentProduct().getName();
			description = ph.getCurrentProduct().getDescription();
			price = ph.getCurrentProduct().getPrice();
//			expireDate = ph.getCurrentProduct().getTyp;
			inStock = ph.getCurrentProduct().getInStock();

		 
		 
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		Query query = session.createQuery("UPDATE Product set name =:name, description =:description, price =:price, expireDate =:expireDate, source =:source, inStock =:inStock, " + " WHERE ID = :ID");
		query.setParameter("name", name);
		query.setParameter("description", description);
		query.setParameter("price", price);
		query.setParameter("expireDate", expireDate);
		query.setParameter("source", source);
		query.setParameter("inStock", inStock);
		query.setParameter("ID", ph.getCurrentProduct().getId());
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
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