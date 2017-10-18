package hibrnate.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.google.common.base.MoreObjects;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	
	private String custName;
	
	private String password;
	
	@ManyToMany
	private Collection<Coupon> coupons = new ArrayList<>();

	public Customer() {
	}

	public Customer(long id, String custName, String password) {

		this.id = id;
		this.custName = custName;
		this.password = password;

	}
	public Customer( String custName, String password) {

		this.custName = custName;
		this.password = password;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Coupon> getCoupns() {
		return coupons;
	}

	public void setCoupns(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	public void addCoupn(Coupon coupon) {
		this.coupons.add(coupon);
		
	}

	@Override
	public String toString() {
	return	MoreObjects.toStringHelper(this)
		.add("id", this.id)
		.add("customer name", this.custName)
		.add("password", this.password)
		.add("coupons", this.coupons)
		.toString();
	}

}
