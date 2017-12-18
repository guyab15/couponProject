package hibrnate.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.google.common.base.MoreObjects;

@Entity
public class Company{
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String compName;
	
	@NotNull
	private String password;
	
	@NotNull
	private String email;
	
	@OneToMany
	//@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
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
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
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
