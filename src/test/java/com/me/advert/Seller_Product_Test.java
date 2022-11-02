package com.me.advert;

import java.util.Scanner;

import com.me.dao.ProductDAO;
import com.me.dao.SellerDAO;
import com.me.exception.AdvertException;
import com.me.pojo.Product;
import com.me.pojo.Seller;

public class Seller_Product_Test {

	public static void main(String[] args) throws AdvertException {
		// TODO Auto-generated method stub
		
		Seller seller = new Seller();
		seller.setName("Lux cozy");
		seller.setPassword("holtrox");
		
		
		SellerDAO sellerdao = new SellerDAO();
		sellerdao.create(seller);
		
		
		Product product1 = new Product();
		
		product1.setName("Raa");
		product1.setDescription("rihanna ");
		
		product1.setPrice("10.46");
		

		Product product2 = new Product();
		
		product2.setName("Ram:lobo");
		product2.setDescription("lllllllllllllllllllll");
		
		product2.setPrice("14.89");
		
		
		ProductDAO productdao = new ProductDAO();
		
		productdao.create(product1,seller);
		
		
		productdao.create(product2,seller);
	
		
		
	}

}
