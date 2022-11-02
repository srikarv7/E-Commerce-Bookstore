package com.me.pojo;

import java.util.ArrayList;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


import com.me.exception.AdvertException;

@Entity
@Table(name="consumertable")
public class Consumer {
	
	@Id
	@Column(name="CONSUMER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic
	@Column(name="CONSUMER_NAME")
	private String name;
	
	@Basic
	@Column(name="CONSUMER_PASSWORD")
	private String password;
	
	/*
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CONSUMERTABLE_ID")
	private Set<Order> orders;
	*/
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CONSUMERT_PRODUCT",
			joinColumns = { @JoinColumn(name = "CONSUMER_ID") },
			inverseJoinColumns = { @JoinColumn(name="PRODUCT_ID")})
	private List<Product> products;
	
	@Basic
	@Column(name="CONSUMER_FIRSTNAME")
	private String firstName;
	
	@Basic
	@Column(name="CONSUMER_LASTNAME")
	private String lastName;
	
	@Basic
	@Column(name="CONSUMER_PHONE")
	private String phoneNumber;
	
	@Basic
	@Column(name="CONSUMER_EMAIL")
	private String emailId;
	
	@Basic
	@Column(name="CONSUMER_ADDRESS")
	private String Address;
	
	
	public Consumer() {
		this.products = new ArrayList<Product>();

	}
	
	public Consumer(String name, String password) {
		this.name = name;
		this.password = password;
		this.products = new ArrayList<Product>();
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	*/

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
}
