package com.me.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="Product")
public class Product implements Serializable {
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic
	@Column(name="PRODUCT_NAME")
	private String name;
	
	@Basic
	@Column(name="PRODUCT_DESCRIPTION",columnDefinition="TEXT")
	private String description;
	
	@Basic
	@Column(name="PRODUCT_PRICE")
	private String price;
	
	@Basic
	@Column(name="PRODUCT_GENRE")
	private String genre;
	
	@Basic
	@Column(name="PRODUCT_AUTHOR")
	private String author;
	
	@Basic
	@Column(name="PRODUCT_PUBLISHER")
	private String publisher;
		
	@ManyToOne
	private Seller seller;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="PRODUCTTABLE_ID")
	private List<Review> reviews;
	
	private int orderedCount;
		
	/*
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ORDERTABLE_ID")
	private Set<Order> associatedOrders;
	*/

	public Product() {
		this.orderedCount = 0;
		this.reviews = new ArrayList<Review>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String d) {
		this.price = d;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getOrderedCount() {
		return orderedCount;
	}

	public void setOrderedCount(int orderedCount) {
		this.orderedCount = orderedCount;
	}
	
	public int incrementCount() {
		this.orderedCount++;
		return orderedCount;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public double averageRating(){
		if(this.reviews.size()>0) {
		double total = 0.0;
		double number = 0.0;
		for(Review r : this.reviews) {
			
			total = total+Double.parseDouble(r.getRating());
			number++;
		}
		double average = total/number;
		
		return average;}
		else {
			return 0.0;
		}
		
	}
	
	public void addReview(Review review) {
		
		this.reviews.add(review);
		
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	

	/*
	public Set<Order> getAssociatedOrders() {
		return associatedOrders;
	}

	public void setAssociatedOrders(Set<Order> associatedOrders) {
		this.associatedOrders = associatedOrders;
	}
	
	public void addOrder(Order order) {
		
		this.associatedOrders.add(order);
		
	}
	*/
	
	
}
