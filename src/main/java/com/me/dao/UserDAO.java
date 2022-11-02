package com.me.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.exception.AdvertException;
import com.me.pojo.User;

public class UserDAO extends DAO {

    public UserDAO() {
    }

    public User get(User user)
            throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(User.class);
    		crit.add(Restrictions.eq("name", user.getName()));
    		crit.add(Restrictions.eq("password", user.getPassword()));
    		User loggedInUser = (User) crit.uniqueResult();
    		commit();
    		return loggedInUser;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not get user " + user.getName(), e);
        }
    }

    public User create(User user)
            throws AdvertException {
        try {
            begin();
            getSession().save(user);
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(User user)
            throws AdvertException {
        try {
            begin();
            getSession().delete(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete user " + user.getName(), e);
        }
    }
}