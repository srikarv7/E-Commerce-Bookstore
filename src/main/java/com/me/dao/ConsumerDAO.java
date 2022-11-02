package com.me.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.me.exception.AdvertException;
import com.me.pojo.Consumer;
import com.me.pojo.Product;
import com.me.pojo.User;

public class ConsumerDAO extends DAO {

	public Consumer get(Consumer consumer)
            throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(Consumer.class);
    		crit.add(Restrictions.eq("name", consumer.getName()));
    		crit.add(Restrictions.eq("password", consumer.getPassword()));
    		Consumer loggedInConsumer = (Consumer) crit.uniqueResult();
    		commit();
    		return loggedInConsumer;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not get user " + consumer.getName(), e);
        }
    }

    public Consumer create(Consumer consumer)
            throws AdvertException {
        try {
            begin();
            getSession().save(consumer);
            commit();
            return consumer;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating user: " + e.getMessage());
        }
    }
    
    public Consumer update(Consumer consumer)
            throws AdvertException {
        try {
            begin();
            getSession().merge(consumer);
            commit();
            return consumer;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating user: " + e.getMessage());
        }
    }
    
    public void addProducts(Consumer consumer,String[] products,ProductDAO productdao) throws AdvertException {
    	try {
    	//convert all string of products to long array of products
    	int n = products.length;
    	long[] productids = new long[n];
    	for(int i=0;i<n;i++) {
    		productids[i] = Long.parseLong(products[i]);	
    	}
    	
    	//begin();
    	
    	for(long l: productids) {
    		Product product = productdao.getProductByid(l);
    		begin();
    		consumer.addProduct(product);
    		commit();
    		product.incrementCount();
    	}
    	begin();
        getSession().merge(consumer);
        commit();
    	//commit();
    	
    	} catch(HibernateException e) {
    		rollback();
    		throw new AdvertException("Exception while adding products to the consumer: " + e.getMessage());
    	}
    	
    		
    }
    
    public Product addSingleProduct(Consumer consumer,String productid,ProductDAO productdao) throws AdvertException {
    	try {
    	//convert all string of products to long array of products
    	
    		long id = Long.parseLong(productid);
    		Product product = productdao.getProductByid(id);
    		product.incrementCount();
    		begin();
    		consumer.addProduct(product);
    		getSession().merge(consumer);
    		getSession().merge(product);
    		commit();
    		product.incrementCount();
    		return product;

    	//commit();
    	
    	} catch(HibernateException e) {
    		rollback();
    		throw new AdvertException("Exception while adding product to the consumer: " + e.getMessage());
    	}
	
    }
    
    public void delete(Consumer consumer)
            throws AdvertException {
        try {
            begin();
            getSession().delete(consumer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete user " + consumer.getName(), e);
        }
    }
	
}
