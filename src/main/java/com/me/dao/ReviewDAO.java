package com.me.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.me.exception.AdvertException;
import com.me.pojo.Category;
import com.me.pojo.Consumer;
import com.me.pojo.Product;
import com.me.pojo.Review;
import com.me.pojo.Seller;

public class ReviewDAO extends DAO {
	
	public Review get(String id) throws AdvertException {
        try {
            begin();
            Criteria crit = getSession().createCriteria(Review.class);
    		crit.add(Restrictions.eq("id", Long.parseLong(id)));
    		Review review = (Review) crit.uniqueResult();
            commit();
            return review;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not obtain the review with id " + id + " " + e.getMessage());
        }
    }
	
	public Product create(Product product,Review review, Consumer consumer)
            throws AdvertException {
        try {
            begin();
            product.addReview(review);
            review.setProduct(product);
            review.setConsumer(consumer);
            
            Session session = getSession();
            session.save(review);
            session.merge(product);
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating product: " + e.getMessage());
        }
    }
	
	public List<Review> customerProductReviewList(Product product, Consumer consumer) throws AdvertException {
    	List<Review> reviews = new ArrayList<Review>();
		
		try {
			begin();
			Criteria crit = getSession().createCriteria(Review.class);
			/*Review r = new Review();
			r.setProduct(product);
			r.setConsumer(consumer);
			Example ex = Example.create(r);*/
			
			Criterion c1 = Restrictions.eq("consumer",consumer);
			Criterion c2 = Restrictions.eq("product",product);
			crit.add(c1);
			crit.add(c2);
			reviews = crit.list();
		    commit();
			return reviews;
		} catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not list the products", e);
        }
    }
	
	public void delete(Review review) throws AdvertException {
        try {
            begin();
            getSession().delete(review);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete the review", e);
        }
    }
	
	public void update(Review review) throws AdvertException {
        try {
            begin();
            getSession().merge(review);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not update review", e);
        }
    }

}
