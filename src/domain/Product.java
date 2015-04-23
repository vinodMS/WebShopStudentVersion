package domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Version;

@Entity
@Inheritance
@DiscriminatorColumn(name="productType", discriminatorType=DiscriminatorType.STRING,length=20)
public abstract class Product implements Comparable<Product> {
	private static long currentId =1;
	
	@Id
	@Column(name="ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Version
	@Column(nullable = false)
	private int version;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private double price; // in euro, internationalization can come later
	
	@Column(name="productType", insertable = false, updatable = false)
	protected String productType;
	
	@Column(name="inStock")
	private int inStock;
	
	
	public Product(String name, String description, double price, int inStock) {
		super();
		this.id = currentId++; // Simple way of getting an unique id. To be deferred to the DBMS later.
		this.name = name;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		if (this instanceof Honey) {
			productType = "Honey";
		}
		else if (this instanceof Wax) {
			productType = "Wax";
		}
		else if (this instanceof Miscellaneous) {
			productType = "Miscellaneous";
		}
		else {
			productType = "Unknown";
		}
	}
	
	public Product(){}
	
	public abstract String toString();
	
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
	public long getId() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getType() {
		return productType;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public final int compareTo(Product other) {
		if (this.getClass().toString().compareTo(other.getClass().toString()) == 0) {
			return this.name.compareTo(other.name);
		}
		else {
			return this.getClass().toString().compareTo(other.getClass().toString());
		}
	}
}
