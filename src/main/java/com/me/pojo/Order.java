package com.me.pojo;

import java.util.Date;
import java.util.Map;
import java.util.Set;

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
@Table(name="ordertable")
public class Order {
	
	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// one to one on product
	//private Product product;
	
	//many orders can be ordered by one consumer
	@ManyToOne
	private Consumer consumer;
	
	/*
	//Set of products
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ORDERPRODUCT_ID")
	private Set<Product> products;
	*/
	
	@ManyToOne
	private Product product;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
}
