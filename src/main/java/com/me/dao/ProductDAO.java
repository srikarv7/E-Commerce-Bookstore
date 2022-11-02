package com.me.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.me.exception.AdvertException;
import com.me.pojo.Advert;
import com.me.pojo.Category;
import com.me.pojo.Product;
import com.me.pojo.Seller;



public class ProductDAO extends DAO {
	
	
	public Product getProduct(Product product)
            throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(Product.class);
    		crit.add(Restrictions.eq("name", product.getName()));
    		crit.add(Restrictions.eq("genre", product.getGenre()));
    		crit.add(Restrictions.eq("author", product.getAuthor()));
    		crit.add(Restrictions.eq("publisher", product.getPublisher()));
    		Product currentProduct = (Product) crit.uniqueResult();
    		commit();
    		return currentProduct;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not get product " + product.getName(), e);
        }
    }
	
	public Product getProductByid(long identity)
            throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(Product.class);
            crit.add(Restrictions.eq("id", identity));
    		Product currentProduct = (Product) crit.uniqueResult();
    		commit();
    		return currentProduct;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not get product " + identity, e);
        }
    }
	/*
	public Product getMultipleProductsByid(String[] products)
            throws AdvertException {
        try {
            begin();
            int n = products.length;
        	long[] productids = new long[n];
        	for(int i=0;i<n;i++) {
        		productids[i] = Long.parseLong(products[i]);	
        	}
        	
            Criteria crit = getSession().createCriteria(Product.class);
            //crit.add(Restrictions.eq("id", identity));
    		Product currentProduct = (Product) crit.uniqueResult();
    		commit();
    		return currentProduct;
        } catch (HibernateException e) {
            rollback();
            //throw new AdvertException("Could not get product " + identity, e);
        }
    }
    */
    
	public Product create(Product product,Seller seller)
            throws AdvertException {
        try {
            begin();
            product.setSeller(seller);
            seller.addProduct(product);
            Session session = getSession();
            session.save(product);
            session.merge(seller);
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating product: " + e.getMessage());
        }
    }
	
	public Product update(Product product)
            throws AdvertException {
        try {
            begin();
            getSession().merge(product);
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while updating product: " + e.getMessage());
        }
    }
	
	public void delete(Product product)
            throws AdvertException {
        try {
            begin();
            getSession().delete(product);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete product", e);
        }
    }
	
	public List<Product> list() throws AdvertException {
    	List<Product> products = new ArrayList<Product>();
		
		try {
			begin();
			Criteria crit = getSession().createCriteria(Product.class);
			products = crit.list();
		    commit();
			return products;
		} catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not list the products", e);
        }
    }

}
