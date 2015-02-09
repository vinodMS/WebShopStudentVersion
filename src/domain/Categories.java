package domain;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import java.io.Serializable;


@SessionScoped
@Named

public class Categories implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Product> productWax;

    public Categories() {
//    	products = new ArrayList<Product>();
//    	products.add(new Honey("Honey1", "Nice honey indeed", null, null, 20));
//    	
//    	products.add(new Honey("Honey2", "Nice honey indeed", null, null, 20));
//    	products.add(new Wax("Wax1", "Nice wax eh?", 40));
////    	products.add(new Miscellaneous("Other1", "what is this other?", null, null, 30));
    	
 
    	   Session session = HibernateUtil.getSessionFactory().openSession();
    	    
    			session.beginTransaction();
//    			Product wax = new Wax("Wax1", "Nice wax eh?", 40);
//    			Product honey = new Honey("Honey1", "Nice honey indeed", null, null, 20);
//    			Product miscellaneous= new Miscellaneous("Other1", "what is this other?", null, null, 20);
//    			Flower flower = new Flower("Rose", "Red roses with big tuples");
//    			session.save(wax);
//    			session.getTransaction().commit();
//    			session.close();
//    			System.out.println("Products saved.");
    			
//    			String hql = "From User user";
//    			Query query = session.createQuery(hql);
//    			List<User> results = query.list();
//    			
//    		    for(User item:results){
//    		    	System.out.println(item);
//    		    }
    			
    			
    			session.getTransaction().commit();
    			session.close();
 }
    public List<Product> getProductWax() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("FROM Product WHERE productType= Wax");
    	List<Product> products = query.list();
		session.close();    	
    	return products;
    }
    
    public List<Product> getProductMisc() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("FROM Product WHERE productType= Miscellaneous");
    	List<Product> products = query.list();
		session.close();    	
    	return products;
    }
    
    public Product getProduct(Long pid)	{
        for (Product p : productWax)	{
            if (p.getId() == pid)	{
            	return p;
            }
        }
        return null;
   }
    
    public void setProductWax(List<Product> productWax) {
		this.productWax = productWax;
	}

}

