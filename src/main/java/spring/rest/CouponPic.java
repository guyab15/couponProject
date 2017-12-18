package spring.rest;

import java.util.Date;

import com.google.common.base.MoreObjects;

import hibrnate.entity.Company;
import hibrnate.entity.CouponType;

public class CouponPic {
	
	private long id;
	
	
	private String title;
	
	
	private Date startDate;
	
	
	private Date endDate;
	
	
	private int amount;
	
	
	private CouponType type;
	
	private String message;
	
	
	private double price;

	private byte[] image;
	
	
	private Company company;


	public CouponPic() {

	}

	public CouponPic(long id, String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, byte[] image) {
		
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	
	public CouponPic(long id,String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
	

	@Override
	public String toString() {
	
		return MoreObjects.toStringHelper(this)
						.add("id", this.id)
						.add("title", this.title)
						.add("start date", this.startDate)
						.add("end date", this.endDate)
						.add("amount", this.amount)
						.add("type", this.type)
						.add("message", this.message)
						.add("price", this.price)
						.add("image", this.image)
						.toString();
	}

}
