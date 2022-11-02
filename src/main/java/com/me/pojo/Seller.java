package com.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
	
	@Id
	@Column(name="SELLER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic
	@Column(name="SELLER_NAME")
	private String name;
	
	@Basic
	@Column(name="SELLER_PASSWORD")
	private String password;
	
	@Basic
	@Column(name="SELLER_FIRSTNAME")
	private String firstName;
	
	@Basic
	@Column(name="SELLER_LASTNAME")
	private String lastName;
	
	@Basic
	@Column(name="SELLER_PHONE")
	private String phoneNumber;
	
	@Basic
	@Column(name="SELLER_EMAIL")
	private String emailId;
	
	@Basic
	@Column(name="SELLER_ADDRESS")
	private String Address;
	
	//many to one mapping between product and Seller
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="SELLERTABLE_ID")
	private Set<Product> products;
	
	public Seller() {
		this.products = new HashSet<Product>();
	}
	
	public Seller(long id, String name, String password) {
		this.name = name;
		this.password = password;
		this.products = new HashSet<Product>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public void addProduct(Product product) {
		this.getProducts().add(product);
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
