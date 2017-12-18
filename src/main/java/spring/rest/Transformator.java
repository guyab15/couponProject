package spring.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import hibrnate.entity.Coupon;
import spring.storage.FileSystemStorageService;
@Configuration
public class Transformator {
	@Autowired
	private FileSystemStorageService storage;
	
	
public ArrayList<CouponPic> truns(Collection<Coupon> from, ArrayList<CouponPic> to) throws IOException	{
	for(Coupon c : from){
		
		
			byte[] image = storage.getImageBytes(c.getImage());
		
		
		
		to.add(new CouponPic(c.getId(), c.getTitle(), c.getStartDate(), c.getEndDate(), c.getAmount(), c.getType(), c.getMessage(), c.getPrice(), image));
	}
	
	return to;
}

	
	
}
