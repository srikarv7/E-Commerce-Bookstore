package com.me.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.me.exception.AdvertException;
import com.me.pojo.Order;
import com.me.pojo.Product;
import com.me.pojo.User;
import com.me.pojo.Consumer;

public class OrderDAO extends DAO {
	
	public OrderDAO() {
		
	}
	
	/*
	public Order get(Order order)
            throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(User.class);
    		crit.add(Restrictions.eq("name", user.getName()));
    		User loggedInUser = (User) crit.uniqueResult();
    		commit();
    		return loggedInUser;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not get user " + user.getName(), e);
        }
    }
    */

    public Order create(Order order,Consumer consumer)
            throws AdvertException {
        try {
            begin();
            Product product = order.getProduct();
            //product.addOrder(order);
            //consumer.addOrder(order);
            getSession().save(order);
            commit();
            return order;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating user: " + e.getMessage());
        }
    }
    
    /*

    public void delete(Order order)
            throws AdvertException {
        try {
            begin();
            getSession().delete(order);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete user " + order.getName(), e);
        }
    }
	*/
}
