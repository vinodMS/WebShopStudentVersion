package domain;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import java.io.Serializable;


@SessionScoped
@Named(value="catalog") 

public class Catalog implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Product> products;
    public Catalog() { }
    
    
    public List<Product> getProduct() {
    	return getProductFromDB();
    }
    
    public Product getProduct(Long pid)	{
    	List<Product> products = getProductFromDB();
        for (Product p : products)	{
            if (p.getId() == pid)	{
            	return p;
            }
        }
        return null;
   }
    
    public List<Product> getProductFromDB()   {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	String hql = "From Product product";
    	Query query = session.createQuery(hql);
    	session.getTransaction().commit();
    	List<Product> products = query.list();
		session.close();
		return products;
		
    }
    
	public void save(Product product){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();
	}
   
}

