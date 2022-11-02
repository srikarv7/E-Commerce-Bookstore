package com.me.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.me.exception.AdvertException;
import com.me.pojo.Advert;
import com.me.pojo.Product;
import com.me.pojo.Seller;
import com.me.pojo.User;

public class SellerDAO extends DAO {
	
	public Seller get(Seller seller)
            throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(Seller.class);
    		crit.add(Restrictions.eq("name", seller.getName()));
    		crit.add(Restrictions.eq("password", seller.getPassword()));
    		Seller loggedInSeller = (Seller) crit.uniqueResult();
    		commit();
    		return loggedInSeller;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not get seller " + seller.getName(), e);
        }
    }

    public Seller create(Seller seller)
            throws AdvertException {
        try {
            begin();
            getSession().save(seller);
            commit();
            return seller;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating seller: " + e.getMessage());
        }
    }

    public void delete(Seller seller)
            throws AdvertException {
        try {
            begin();
            getSession().delete(seller);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete seller " + seller.getName(), e);
        }
    }
    
    public Seller update(Seller seller)
            throws AdvertException {
        try {
            begin();
            getSession().merge(seller);
            commit();
            return seller;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while updating seller: " + e.getMessage());
        }
    }
    
    /*
    public List<Product> productslist() throws AdvertException {
    	
    	List<Product> products = new ArrayList<Product>();
		
		try {
			begin();
			Criteria crit = getSession().createCriteria(Advert.class);
			adverts = crit.list();
		    commit();
			return adverts;
		} catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not list the adverts", e);
        }
    }
    */

}
