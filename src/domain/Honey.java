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
	
	@Column(name="expireDate")
	private Date expireDate;
	@ManyToOne
    @JoinColumn(name="source")
	private Flower source;
	
	public Honey(String name, String description, Date expireDate, Flower source, double price, int inStock) {
		super(name, description, price, inStock);
		this.expireDate = expireDate;
		this.source = source;
	}
	
	public Honey(){}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Flower getSource() {
		return source;
	}

	public void setSource(Flower source) {
		this.source = source;
	}
	@Override
	public String toString(){
		return "Honey: " + source.getName() + ", best before " + expireDate + ", " + getDescription();
	}
}
