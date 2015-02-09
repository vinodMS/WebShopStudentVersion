package domain;

// Special class for output in theShoppingCartBean; do not store in database
// In the database, ShoppingCarts with status COMPLETED are stored.
public class OrderItem implements Comparable<OrderItem> {
	private long id;
	private String name;
	private double price;
	private int amount;
	
//	public OrderItem() {
//		
//	}

	public OrderItem(long id, String name, double price, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(OrderItem other) {
		return this.name.compareTo(other.name);
	}
}
