package hibrnate.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.common.base.MoreObjects;

@Entity
public class Company{
	@Id
	@GeneratedValue
	private long id;

	private String compName;

	private String password;

	private String email;
	
	@OneToMany
	private Collection<Coupon> coupons = new ArrayList<>();


	public Company() {

	}

	public Company(long id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	public Company(String compName, String password, String email) {
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
//	@JsonProperty("coupons")
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
//	@JsonProperty("coupons1")
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	

	
	
	@Override
	public String toString() {
		
	return MoreObjects.toStringHelper(this)
					   .add("id", this.id)
					   .add("comp name", this.compName)
					   .add("password", this.password)
					   .add("email", this.email)
					   .add("list coupon", this.coupons)
					   .toString();
	
	}

}
