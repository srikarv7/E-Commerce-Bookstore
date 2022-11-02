package com.me.advert;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.me.dao.ConsumerDAO;
import com.me.dao.ProductDAO;
import com.me.dao.SellerDAO;
import com.me.dao.UserDAO;
import com.me.exception.AdvertException;
import com.me.pojo.Consumer;
import com.me.pojo.Product;
import com.me.pojo.Seller;
import com.me.pojo.User;

@Controller
public class SellerController {
	
	// Seller role Page
	
		@RequestMapping(value = "/Sellerhome", method = RequestMethod.GET)
		@ResponseBody
		public ModelAndView getAdminHomePage(HttpSession session) {
			Seller seller = (Seller) session.getAttribute("seller");
			if(seller != null) {
				ModelAndView model = new ModelAndView("Sellerhome");
				model.addObject("seller", seller);
				return model;
			} else {
				ModelAndView model = new ModelAndView("redirect:/");
				return model;
			}
			
		}
		
		// Seller Logout 
		@RequestMapping("seller/logout.htm")
		public ModelAndView getLogout(HttpSession session){
			session.invalidate();
			ModelAndView model = new ModelAndView("redirect:/");
			return model;
		}
			
			
		@GetMapping("seller/register.htm")
		public String showRegistrationForm(ModelMap model) {
			Seller seller = new Seller(); // FormBackingObject
			model.addAttribute("seller", seller);
			return "addSellerForm";
		}

		@PostMapping("seller/register.htm")
		public String handleRegistrationForm(ModelMap model,@ModelAttribute("seller") Seller seller, BindingResult result, SessionStatus status, SellerDAO sellerdao) throws AdvertException {
			if (result.hasErrors())
				return "addSellerForm";
			else {
				Seller alreadySeller = sellerdao.get(seller);
				if(alreadySeller == null) {
					sellerdao.create(seller);
					status.setComplete();
					return "addedSeller";
				} else {
					model.addAttribute("seller", alreadySeller);
					return "sellerregisterError";
				}
				
			}
		}
		
		//Seller Login
		@GetMapping("seller/login.htm")
		public String showLoginForm(ModelMap model) {
			Seller seller = new Seller(); // FormBackingObject
			model.addAttribute("seller", seller);
			return "loginSeller";
		}
		
		@PostMapping("seller/login.htm")
		public ModelAndView handleLogin(@ModelAttribute("seller") Seller seller, BindingResult result, SessionStatus status, SellerDAO sellerdao, HttpSession session) throws AdvertException {
			if (result.hasErrors()) {
				ModelAndView model = new ModelAndView("addSellerForm");
				return model;
			} else {
				Seller retrivedSeller = sellerdao.get(seller);
				if(retrivedSeller != null) {
					session.setAttribute("seller", retrivedSeller);
					status.setComplete();
					ModelAndView model = new ModelAndView("redirect:/Sellerhome");
					return model;
				} else {
					ModelAndView model = new ModelAndView("loginError");
					return model;
				}
				
			}
		}
		
		@GetMapping("seller/viewProfile.htm")
		public String viewForm(ModelMap model,HttpSession session) {
			Seller seller = (Seller) session.getAttribute("seller");
			if(seller == null) {
				
				return "sellerNotFound";

			}else {
			model.addAttribute("seller", seller);
			return "viewSellerForm";
			}
		}
		
		//Seller profile update
		@GetMapping("seller/updateProfile.htm")
		public String showUpdateForm(ModelMap model,HttpSession session) {
			Seller seller = (Seller) session.getAttribute("seller"); // The currently logged in seller
			if(seller == null) {
				
				return "sellerNotFound";

			}
			model.addAttribute("seller", seller);
			return "updateSellerForm";
		}

		@PostMapping("seller/updateProfile.htm")
		public ModelAndView handleUpdateForm(ModelMap model,  SessionStatus status, ConsumerDAO consumerdao,HttpSession session, HttpServletRequest request,SellerDAO sellerdao) throws AdvertException {
			
			Seller seller = (Seller) session.getAttribute("seller");
			
			seller.setPassword(request.getParameter("password"));
			seller.setFirstName(request.getParameter("fname"));
			seller.setLastName(request.getParameter("lname"));
			seller.setPhoneNumber(request.getParameter("phoneNumber"));
			seller.setEmailId(request.getParameter("emailId"));
			seller.setAddress(request.getParameter("address"));
			
			sellerdao.update(seller);
			
			ModelAndView model1 = new ModelAndView("redirect:/seller/viewProfile.htm");
			return model1;
		}
		
		@GetMapping("seller/addproduct.htm")
		public String addProductForm(ModelMap model,HttpSession session) {
			
			Seller seller = (Seller) session.getAttribute("seller"); // The currently logged in seller
			if(seller == null) {
				
				return "sellerNotFound";

			}
			
			Product product = new Product(); // FormBackingObject
			model.addAttribute("product", product);
			return "SellerAddProductView";
		}

		@PostMapping("seller/addproduct.htm")
		public String handleAddProduct(ModelMap model,@ModelAttribute("product") Product product, BindingResult result, SessionStatus status, SellerDAO sellerdao, ProductDAO productdao,HttpSession session) throws AdvertException {
			if (result.hasErrors())
				return "SellerAddProductView";
			else {
				Product product1 = productdao.getProduct(product);
				//Seller alreadySeller = sellerdao.get(seller);
				if(product1 == null) {
					Seller seller = (Seller)session.getAttribute("seller");
					
					productdao.create(product,seller);
					//seller.getProducts().add(product);
					//seller.addProduct(product);
					//sellerdao.update(seller);
					status.setComplete();
					return "Sellerhome";
				} else {
					model.addAttribute("product", product1);
					return "sellerregisterError";
				}
				
			}
		}
		
		@GetMapping("seller/viewproducts.htm")
		public String viewProducts(ModelMap model,HttpSession session) {
			Seller seller = (Seller) session.getAttribute("seller");
			//Seller seller = (Seller) session.getAttribute("seller"); // The currently logged in seller
			if(seller == null) {
				
				return "sellerNotFound";

			}
			Set<Product> products = seller.getProducts();
			model.addAttribute("products", products);
			return "sellerViewProducts";
		}
		
		@GetMapping("seller/editproduct.htm")
		public String editProduct(HttpServletRequest request,ModelMap model,HttpSession session,ProductDAO productdao) throws AdvertException {
			/*Seller seller = (Seller) session.getAttribute("seller");
			Set<Product> products = seller.getProducts();
			model.addAttribute("products", products);*/
			
			Seller seller = (Seller) session.getAttribute("seller"); // The currently logged in seller
			if(seller == null) {
				
				return "sellerNotFound";

			}
			
			String productid = request.getParameter("product");
			long id = Long.parseLong(productid);
			Product product = productdao.getProductByid(id);
			
			
			
			return "editProduct";
		}
		
		@PostMapping("seller/editproduct.htm")
		public ModelAndView handleEditProduct(HttpServletRequest request,ModelMap model,HttpSession session,ProductDAO productdao,SellerDAO sellerdao) throws AdvertException {
			/*Seller seller = (Seller) session.getAttribute("seller");
			Set<Product> products = seller.getProducts();
			model.addAttribute("products", products);*/
			
			String productid = request.getParameter("product");
			long id = Long.parseLong(productid);
			Product product = productdao.getProductByid(id);
			
			product.setName(request.getParameter("productName"));
			product.setDescription(request.getParameter("descripton"));
			product.setPrice(request.getParameter("price"));
			product.setGenre(request.getParameter("genre"));
			product.setAuthor(request.getParameter("author"));
			product.setPublisher(request.getParameter("publisher"));
			
			productdao.update(product);
			
			ModelAndView model1 = new ModelAndView("redirect:/seller/viewproducts.htm");
			return model1;
		}
			
}
