package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Miscellaneous")
public class Miscellaneous extends Product {
	@Column(name="expireDate")
	private Date expireDate;
	@Column(name="areaOfApplication")
	private String areaOfApplication;
	
	public Miscellaneous(String name, String description, Date expireDate, String areaOfApplication, double price, int inStock) {
		super(name, description, price, inStock);
		this.expireDate = expireDate;
		this.areaOfApplication = areaOfApplication;
	}
	
	public Miscellaneous(){}
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getAreaOfApplication() {
		return areaOfApplication;
	}

	public void setAreaOfApplication(String areaOfApplication) {
		this.areaOfApplication = areaOfApplication;
	}
	
	@Override
	public String toString(){
		return "Other bee product: " + getName() + ", best before " + expireDate + ", " + getDescription() + ", " + areaOfApplication;
	}
}
