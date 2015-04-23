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
	private long wax;
	private long misc;
	private long honey;
	private long all;
	
    public long getWax() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("SELECT COUNT(ID) From Product WHERE productType= Wax");
    	List<?> list = query.list();
    	System.out.println("this is" +list);
    	wax =  (Long) list.get(0);
		return wax;
	}
	public void setWax(long wax) {
		this.wax = wax;
	}
	public long getMisc() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("SELECT COUNT(ID) From Product WHERE productType= Miscellaneous");
    	List<?> list = query.list();
    	System.out.println("this is" +list);
    	misc =  (Long) list.get(0);
		return misc;
	}
	public void setMisc(long misc) {
		this.misc = misc;
	}
	public long getHoney() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("SELECT COUNT(ID) From Product WHERE productType= Honey");
    	List<?> list = query.list();
    	System.out.println("this is" +list);
    	honey =  (Long) list.get(0);
		return honey;
	}
	public void setHoney(long honey) {
		this.honey = honey;
	}
	public long getAll() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("SELECT COUNT(ID) From Product");
    	List<?> list = query.list();
    	System.out.println("this is" +list);
    	all =  (Long) list.get(0);
		return all;
	}
	public void setAll(long all) {
		this.all = all;
	}
	public List<Product> getProductWax() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("FROM Product WHERE productType= Wax");
    	@SuppressWarnings("unchecked")
		List<Product> products = query.list();
		session.close();    	
    	return products;
    }
    
    public List<Product> getProductMisc() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("FROM Product WHERE productType= Miscellaneous");
    	@SuppressWarnings("unchecked")
		List<Product> products = query.list();
		session.close();    	
    	return products;
    }
    
    public List<Product> getProductHoney() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("FROM Product WHERE productType= Honey");
    	@SuppressWarnings("unchecked")
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

