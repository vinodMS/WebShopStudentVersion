package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Honey")
public class Honey extends Product {
	
	@Column(name="expiryDate")
	private Date expiryDate;
	@ManyToOne
    @JoinColumn(name="source")
	private Flower source;
	
	public Honey(String name, String description, Date expiryDate, Flower source, double price, int inStock) {
		super(name, description, price, inStock);
		this.expiryDate = expiryDate;
		this.source = source;
	}
	
	public Honey(){}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Flower getSource() {
		return source;
	}

	public void setSource(Flower source) {
		this.source = source;
	}
	@Override
	public String toString(){
		return "Honey: " + source.getName() + ", best before " + expiryDate + ", " + getDescription();
	}
}
