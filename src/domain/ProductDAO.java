package domain;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;

@Named 
@RequestScoped

public class ProductDAO {
	
    Session session = HibernateUtil.getSessionFactory().openSession();
    
	public void save(Product product){
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();
	}
} 