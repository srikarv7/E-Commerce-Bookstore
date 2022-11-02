package com.me.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.me.exception.AdvertException;
import com.me.pojo.Category;
import com.me.pojo.User;

public class CategoryDAO extends DAO {

    public Category get(String title) throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(Category.class);
    		crit.add(Restrictions.eq("title", title));
    		Category category = (Category) crit.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }

    public List<Category> list() throws AdvertException {
    	List<Category> categories = new ArrayList<Category>();
		
		try {
			begin();
			Criteria crit = getSession().createCriteria(Category.class);
			categories = crit.list();
		    commit();
			return categories;
		} catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not list the categories", e);
        }
    }

    public Category create(Category category) throws AdvertException {
        try {
            begin();
            getSession().save(category);
            commit();
            return null;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating category: " + e.getMessage());
        }
    }

    public void save(Category category) throws AdvertException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not save the category", e);
        }
    }

    public void delete(Category category) throws AdvertException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete the category", e);
        }
    }
}