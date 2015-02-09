package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;
	private static long currentId =1;
	private long id;
	private Date date;
	private User user;
	private Map<Product, Integer> orders = new HashMap<Product, Integer>();;
	private OrderStatus status;
	
	public ShoppingCart() {
		this(new Date());
	}
	
	public ShoppingCart(Date date) {
		super();
		this.id = currentId++; // Simple way of getting an unique id. To be deferred to the DBMS later.
		this.date = date;
		this.status = OrderStatus.EMPTY;
	}
	
	public ShoppingCart(Date date, User user) {
		this(date);
		this.user = user;
	}
	
	public ShoppingCart(Date date, User user, OrderStatus status) {
		this(date, user);
		if (((status == OrderStatus.COMPLETED) || (status == OrderStatus.PAID)) && (this.getUser() == null)) {
			throw new IllegalStateException("No user specified for complete or paid orders");
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Product, Integer> getOrders() {
		return orders;
	}

	public void setOrders(Map<Product, Integer> orders) {
		this.orders = orders;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setCompleted(OrderStatus status) throws IllegalStateTransitionException {
		if ((status == OrderStatus.COMPLETED) && (this.getUser() == null)) {
			throw new IllegalStateTransitionException(this.getStatus().toString(), OrderStatus.COMPLETED.toString(), "Customer empty");
		}
		if ((status == OrderStatus.PAID) && (this.getUser() == null)) {
			throw new IllegalStateTransitionException(this.getStatus().toString(), OrderStatus.PAID.toString(), "Customer empty");
		}
		if ((status == OrderStatus.PAID) && (this.getStatus() != OrderStatus.COMPLETED)) {
			throw new IllegalStateTransitionException(this.getStatus().toString(), OrderStatus.PAID.toString(), "Can only go to paid for completed orders");
		}
		this.status = status;
	}

	public long getId() {
		return id;
	}
	
	public void addToCart(Product product) {
		if (orders.containsKey(product)) {
			int amount = orders.get(product);
			amount ++;
			orders.put(product, amount);
		}
		else {
			orders.put(product, 1);
		}
	}
	
	public void removeFromCart(Product product) {
		if (orders.containsKey(product)) {
			int amount = orders.get(product);
			amount --;
			if (amount <= 0) {
				orders.remove(product);
			}
			else {
				orders.put(product, amount);
			}
		}
	}

	public int getNumberOfItems() {
		Set<Product> uniqueProducts = orders.keySet();
		int amount = 0;
		for (Product aProduct: uniqueProducts) {
			amount += orders.get(aProduct);
		}
		return amount;
	}
	
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return "Order of " + this.user + ", date " + dateFormat.format(cal.getTime());
	}
}

