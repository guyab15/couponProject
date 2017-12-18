package com.couponProject.newCouponProject;

import org.springframework.util.Assert;

public class Test {

	
	public static String lala(String str){
		Assert.notNull(str, "str cannot be null");
		return str;
	}
	
	public static void main(String[] args) {
			System.out.println(lala(null));
	}
		
}
