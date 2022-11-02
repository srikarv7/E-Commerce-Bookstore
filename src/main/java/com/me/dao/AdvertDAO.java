package com.me.dao;

import com.me.exception.AdvertException;
import com.me.pojo.Advert;
import com.me.pojo.Category;
import com.me.pojo.User;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class AdvertDAO extends DAO {

    public Advert create(String title, String message, User user, Category category)
            throws AdvertException {
        try {
            begin();
            Advert advert = new Advert();
            advert.setTitle(title);
            advert.setMessage(message);
            advert.setUser(user);
            advert.addCategory(category);
            getSession().save(advert);
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Advert advert)
            throws AdvertException {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    }
    
    public List<Advert> list() throws AdvertException {
    	List<Advert> adverts = new ArrayList<Advert>();
		
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
}