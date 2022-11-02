package com.me.advert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
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

import com.me.dao.AdvertDAO;
import com.me.dao.CategoryDAO;
import com.me.dao.ConsumerDAO;
import com.me.dao.OrderDAO;
import com.me.dao.ProductDAO;
import com.me.dao.ReviewDAO;
import com.me.dao.UserDAO;
import com.me.exception.AdvertException;
import com.me.pojo.Advert;
import com.me.pojo.Category;
import com.me.pojo.Consumer;
import com.me.pojo.Order;
import com.me.pojo.Seller;
import com.me.pojo.Product;
import com.me.pojo.Review;

import java.util.*;  


import com.me.pojo.User;

@Controller
public class ConsumerController {
	// Consumer role Page
	
				@RequestMapping(value = "/consumerhome", method = RequestMethod.GET)
				@ResponseBody
				public ModelAndView getAdminHomePage(HttpSession session) {
					Consumer consumer = (Consumer) session.getAttribute("consumer");
					if(consumer != null) {
						ModelAndView model = new ModelAndView("consumerHome");
						model.addObject("consumer", consumer);
						return model;
					} else {
						ModelAndView model = new ModelAndView("redirect:/");
						return model;
					}
					
				}
				
				// consumer Logout 
				@RequestMapping("consumer/logout.htm")
				public ModelAndView getLogout(HttpSession session){
					session.invalidate();
					ModelAndView model = new ModelAndView("redirect:/");
					return model;
				}
				
					
				//consumer registration	
				@GetMapping("consumer/register.htm")
				public String showRegistrationForm(ModelMap model) {
					Consumer consumer = new Consumer(); // FormBackingObject
					model.addAttribute("consumer", consumer);
					return "addConsumerForm";
				}

				@PostMapping("consumer/register.htm")
				public String handleRegistrationForm(ModelMap model,@ModelAttribute("consumer") Consumer consumer, BindingResult result, SessionStatus status, ConsumerDAO consumerdao) throws AdvertException {
					if (result.hasErrors())
						return "addConsumerForm";
					else {
						Consumer alreadyConsumer = consumerdao.get(consumer);
						if(alreadyConsumer == null) {
							consumerdao.create(consumer);
							status.setComplete();
							return "addedConsumer";
						} else {
							model.addAttribute("consumer", alreadyConsumer);
							return "consumerregisterError";
						}
						
					}
				}
				
				@GetMapping("consumer/viewProfile.htm")
				public String viewForm(ModelMap model,HttpSession session) {
			
					Consumer consumer = (Consumer) session.getAttribute("consumer");
				
					if(consumer == null) {
						
						return "consumerNotFound";

					}
					model.addAttribute("consumer", consumer);
					return "viewConsumerForm";
				}
				
				//consumer profile update
				@GetMapping("consumer/updateProfile.htm")
				public String showUpdateForm(ModelMap model,HttpSession session) {
					Consumer consumer = (Consumer) session.getAttribute("consumer"); // The currently logged in consumer
					if(consumer == null) {
						
						return "consumerNotFound";

					}
					model.addAttribute("consumer", consumer);
					return "updateConsumerForm";
				}

				@PostMapping("consumer/updateProfile.htm")
				public ModelAndView handleUpdateForm(ModelMap model,  SessionStatus status, ConsumerDAO consumerdao,HttpSession session, HttpServletRequest request) throws AdvertException {
					
					Consumer consumer = (Consumer) session.getAttribute("consumer");
					
					consumer.setPassword(request.getParameter("password"));
					consumer.setFirstName(request.getParameter("fname"));
					consumer.setLastName(request.getParameter("lname"));
					consumer.setPhoneNumber(request.getParameter("phoneNumber"));
					consumer.setEmailId(request.getParameter("emailId"));
					consumer.setAddress(request.getParameter("address"));
					
					consumerdao.update(consumer);
					
					ModelAndView model1 = new ModelAndView("redirect:/consumer/viewProfile.htm");
					return model1;
				}
				
				@GetMapping("consumer/login.htm")
				public String showLoginForm(ModelMap model) {
					Consumer consumer = new Consumer(); // FormBackingObject
					model.addAttribute("consumer", consumer);
					return "loginConsumer";
				}
				
				@PostMapping("consumer/login.htm")
				public ModelAndView handleLogin(@ModelAttribute("consumer") Consumer consumer, BindingResult result, SessionStatus status, ConsumerDAO consumerdao, HttpSession session) throws AdvertException {
					if (result.hasErrors()) {
						ModelAndView model = new ModelAndView("addConsumerForm");
						return model;
					} else {
						Consumer retrivedConsumer = consumerdao.get(consumer);
						if(retrivedConsumer != null) {
							session.setAttribute("consumer", retrivedConsumer);
							status.setComplete();
							ModelAndView model = new ModelAndView("redirect:/consumerhome");
							return model;
						} else {
							ModelAndView model = new ModelAndView("consumerLoginError");
							return model;
						}
						
					}
				}
				
				@GetMapping("consumer/exploreProducts.htm")
				public String orderProducts(ModelMap model, ProductDAO productdao,HttpSession session) throws AdvertException {
					
					Consumer consumer = (Consumer) session.getAttribute("consumer"); // The currently logged in consumer
					if(consumer == null) {
						
						return "consumerNotFound";

					}
					
					Order order = new Order();//Form Backing Object
					List<Product> products = productdao.list();
					model.addAttribute("products",products);
					//model.addAttribute("order",order);
					
					return "viewProductsForm";
				}
				
				@PostMapping("consumer/exploreProducts.htm")
				public String handleorderProduct(HttpSession session, SessionStatus status, ConsumerDAO consumerdao, HttpServletRequest request, ProductDAO productdao, ModelMap model) throws AdvertException {

						String product = request.getParameter("product");
						long id = Long.parseLong(product);
						Product prod = productdao.getProductByid(id);
						model.addAttribute("product",prod);
						
						status.setComplete();
						return "consumerProductDescription";
				}
				
				//below code for the customer to view all his products
				@GetMapping("consumer/viewProduct.htm")
				public String viewOrderedProducts(HttpSession session, ModelMap model, ProductDAO productdao) throws AdvertException {
					Consumer consumer = (Consumer) session.getAttribute("consumer");
					List<Product> products = consumer.getProducts();
					model.addAttribute("products",products);
					
					return "productViewForm";
				}
				
				//below code for the consumer to buy an product and place order
				@GetMapping("consumer/placeOrder.htm")
				public String placeOrder(HttpServletRequest request, HttpSession session, ModelMap model, ProductDAO productdao,ConsumerDAO consumerdao) throws AdvertException {
					String productid = request.getParameter("productid");
					Consumer consumer = (Consumer) session.getAttribute("consumer");
					
					Product product = consumerdao.addSingleProduct(consumer, productid, productdao);
					model.addAttribute("product", product);
					
					return "orderedProduct";
				}
				
				@GetMapping("consumer/writeReview.htm")
				public String writeReview(HttpServletRequest request, ModelMap model, ProductDAO productdao) throws AdvertException {
					String productid = request.getParameter("productid");
					
					Review review = new Review();
					Product product = productdao.getProductByid(Long.parseLong(productid));
					model.addAttribute("product",product);
					model.addAttribute("review",review);
					//model.addAttribute("order",order);
					
					return "writeReviewForm";
				}
				
				@PostMapping("consumer/writeReview.htm")
				public String handleWriteReview(@ModelAttribute("review") Review review,HttpSession session, SessionStatus status, ConsumerDAO consumerdao, HttpServletRequest request, ProductDAO productdao, ModelMap model,ReviewDAO reviewdao) throws AdvertException {
						Product product = productdao.getProductByid(Long.parseLong(request.getParameter("productid")));
						
						Consumer consumer =(Consumer) session.getAttribute("consumer");
						reviewdao.create(product, review, consumer);
						
						model.addAttribute("product",product);
						status.setComplete();
						return "consumerProductDescription";
				}
				
				@GetMapping("consumer/deleteReview.htm")
				public String deleteReview(HttpServletRequest request, ModelMap model, ProductDAO productdao, ReviewDAO reviewdao,SessionStatus status) throws AdvertException {
					
					String productid = request.getParameter("productid");
					long id = Long.parseLong(productid);
					Product product = productdao.getProductByid(id);
					String reviewid = request.getParameter("reviewid");
					
					Review review = reviewdao.get(reviewid);
					reviewdao.delete(review);
					
					model.addAttribute("product",product);
					status.setComplete();
					return "consumerProductDescription";
					
				}
				
				@GetMapping("consumer/updateReview.htm")
				public String updateReview(HttpServletRequest request, ModelMap model, ProductDAO productdao, ReviewDAO reviewdao,SessionStatus status) throws AdvertException {
					
					String productid = request.getParameter("productid");
					long id = Long.parseLong(productid);
					Product product = productdao.getProductByid(id);
					String reviewid = request.getParameter("reviewid");
					
					Review review = reviewdao.get(reviewid);
					
					model.addAttribute("review",review);
					model.addAttribute("product",product);
					status.setComplete();
					return "editReview";
					
				}
				
				@PostMapping("consumer/updateReview.htm")
				public String handleUpdateReview(HttpServletRequest request, ModelMap model, ProductDAO productdao, ReviewDAO reviewdao,SessionStatus status) throws AdvertException {
					
					String productid = request.getParameter("productid");
					long id = Long.parseLong(productid);
					Product product = productdao.getProductByid(id);
					
					Review review = reviewdao.get(request.getParameter("reviewid"));
					
					review.setHeading(request.getParameter("heading"));
					review.setDescription(request.getParameter("description"));
					review.setRating(request.getParameter("ratinig"));
					reviewdao.update(review);
					
					model.addAttribute("product",product);
					status.setComplete();
					return "consumerProductDescription";
					
				}
				
				
								
				//below yet to be implemented
				
				@GetMapping("consumer/buyproduct.htm")
				public String buyProductForm(ModelMap model) {
					Consumer consumer = new Consumer(); // FormBackingObject
					model.addAttribute("consumer", consumer);
					return "addConsumerForm";
				}

				@PostMapping("consumer/buyproduct.htm")
				public String saveProductForm(ModelMap model,@ModelAttribute("consumer") Consumer consumer, BindingResult result, SessionStatus status, ConsumerDAO consumerdao) throws AdvertException {
					if (result.hasErrors())
						return "addConsumerForm";
					else {
						Consumer alreadyConsumer = consumerdao.get(consumer);
						if(alreadyConsumer == null) {
							consumerdao.create(consumer);
							status.setComplete();
							return "addedConsumer";
						} else {
							model.addAttribute("consumer", alreadyConsumer);
							return "consumerregisterError";
						}
						
					}
				}
						
}