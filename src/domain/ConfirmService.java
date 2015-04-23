package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

@Named
@RequestScoped
public class ConfirmService {

	@Inject
	ShoppingCart shoppingcart;
	
	@Inject
	UserHolder userholder;
	
	@Inject
	ProductHolder productHolder;
	
    private String shippingAddress;


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public int availableStocks(Long id, int amount){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		 Query query = session.createQuery("SELECT inStock FROM Product WHERE ID = :ID ");
	     query.setParameter("ID", id);

	     List<?> list = query.list();

	     int inStock = (Integer) list.get(0);
	     
	     return inStock - amount;
	}
	
	
	public void updateStocks(){
		 Iterator<Entry<Product, Integer>> iterator = shoppingcart.getOrders().entrySet().iterator() ;
	        
	        while(iterator.hasNext()){
	        	Map.Entry<Product, Integer> storedProduct = iterator.next();
	        	
	        	int toUpdate = availableStocks(storedProduct.getKey().getId(), storedProduct.getValue());
	        	int version = storedProduct.getKey().getVersion() + 1;
	        	Session session = HibernateUtil.getSessionFactory().openSession();
	    		session.getTransaction().begin();

	    		Query query = session.createQuery("UPDATE Product set inStock = :inStock, version =:version" + " where ID = :ID");
	    		query.setParameter("inStock", toUpdate);
	    		query.setParameter("version", version);
	    		query.setParameter("ID", storedProduct.getKey().getId());
	    		
	    		int result = query.executeUpdate();	
	    		session.getTransaction().commit();
	    		System.out.println("rows updated" + result + storedProduct.getKey().getName());
	    		
	        }
		
		
		
		
	}
	
	public String confirmOrder() throws IllegalStateTransitionException {
		
		//getting current user
		shoppingcart.setUser(userholder.getCurrentUser());
		
		//setting order status
		shoppingcart.setCompleted(OrderStatus.COMPLETED);
		
		//storing shopping order
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(shoppingcart);
		session.getTransaction().commit();
		session.close();
		
		// Updating stocks based on purchase
		updateStocks();
		
		//remove the cart from session bean
		FacesContext fc = FacesContext.getCurrentInstance();
	    fc.getELContext().getELResolver().setValue(fc.getELContext(), null, "shoppingCart", null);
	    
	    //printing Invoice
	    
		//writing to file
		try {
			String content = "Customer Name: " + shoppingcart.getUser().firstName + " " + shoppingcart.getUser().lastName +"\r\n" + 
							 "Address : " + shippingAddress + "\r\n" +
							 "Date Received: " + shoppingcart.getDate() + "\r\n" +
							 "Order Items :" + shoppingcart.getOrders() + "\r\n" +
							 "Money to be received :" + productHolder.subTotal(); 
//			File file = new File("/Users/Vinod/Documents/newfile.txt");
			File file = new File("/Users/Vinod/Documents/" + shoppingcart.getId() + shoppingcart.getUser().firstName + "invoice.txt");
	
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
	
			System.out.println("Done");
			
			}catch (IOException e) {
				e.printStackTrace();
			}
	    
	    
		return "orderComplete";
		
	}
    

}
