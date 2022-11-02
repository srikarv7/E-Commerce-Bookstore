package com.me.advert;

import com.me.dao.ConsumerDAO;
import com.me.dao.OrderDAO;
import com.me.exception.AdvertException;
import com.me.pojo.Consumer;
import com.me.pojo.Order;

public class Consumer_OrderTest {

	public static void main(String[] args) throws AdvertException {
		// TODO Auto-generated method stub
		
		Consumer consumer = new Consumer();
		consumer.setName("Hello");
		consumer.setPassword("Hello");
		
		Order order3 = new Order();
		
		OrderDAO orderdao = new OrderDAO();
		orderdao.create(order3);
		
		Order order2 = new Order();
		order2.setConsumer(consumer);
		//orderdao.create(order2);
		/*
		consumer.getOrders().add(order2);
		consumer.getOrders().add(order1);
		ConsumerDAO consumerdao = new ConsumerDAO();
		consumerdao.create(consumer);
*/
	}

}
