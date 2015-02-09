package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Wax")
public class Wax extends Product {
	
	
	public Wax(String name, String description, double price, int inStock) {
		super(name, description, price, inStock);
	}
	public Wax(){}

	@Override
	public String toString(){
		return "Wax product: " + getName() + "," + getDescription();
	}
}
