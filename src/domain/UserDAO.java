package domain;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;

@Named 
@RequestScoped

public class UserDAO {
	
    Session session = HibernateUtil.getSessionFactory().openSession();
    private UserHolder userHolder;
	public void save(User user){
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	public Integer getId(){
		String hql = "select max(user.id) from User user";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Integer> results = query.list();
		Integer userId = 1;
                if (results.get(0) != null ) {
        	   userId = results.get(0) + 1;
                }
                return userId;
	}
	
	public String userUpdate(User user){
//		String hql = "UPDATE User set firstName = :firstName" +
//					"WHERE ID = :ID";
//		Query query = session.createQuery(hql);
//		query.setParameter("firstName", user.firstName);
//		query.setParameter("id", userHolder.getCurrentUser().getId());
//		int result = query.executeUpdate();
//		System.out.println("Rows affected: " + result);
		System.out.println("User ID is"+userHolder.getCurrentUser().getId());
		return null;	
	}

	public User authenticate(String username,int password){
		String hql = "FROM User user WHERE user.username = '" + username+ "' AND user.password = '" + password+ "'";
    	Query query = session.createQuery(hql);
    	List<User> list = query.list();
    	java.util.Iterator<User> iter = list.iterator();
    	
    	if(list.isEmpty()){
    		return null;
    	}
    	else{
    		while (iter.hasNext()) {
    			User user = iter.next();
    			System.out.println("Person: \"" + user.getFirstName() + "\", " + user.getLastName());
    			return user;
    		}
    		
    	}
    	return null;
		
	}
} 