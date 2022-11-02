package com.me.advert;

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

import com.me.dao.UserDAO;
import com.me.exception.AdvertException;
import com.me.pojo.User;

@Controller
public class UserController {

	// Home Page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getAdminHomePage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			ModelAndView model = new ModelAndView("home");
			model.addObject("user", user);
			return model;
		} else {
			ModelAndView model = new ModelAndView("redirect:/");
			return model;
		}
		
	}
	
	// Logout 
	@RequestMapping("user/logout.htm")
	public ModelAndView getLogout(HttpSession session){
		session.invalidate();
		ModelAndView model = new ModelAndView("redirect:/");
		return model;
	}
		
		
	@GetMapping("user/register.htm")
	public String showRegistrationForm(ModelMap model) {
		User user = new User(); // FormBackingObject
		model.addAttribute("user", user);
		return "addUserForm";
	}

	@PostMapping("user/register.htm")
	public String handleRegistrationForm(ModelMap model,@ModelAttribute("user") User user, BindingResult result, SessionStatus status, UserDAO userdao) throws AdvertException {
		if (result.hasErrors())
			return "addUserForm";
		else {
			User alreadyUser = userdao.get(user);
			if(alreadyUser == null) {
				userdao.create(user);
				status.setComplete();
				return "addedUser";
			} else {
				model.addAttribute("user", alreadyUser);
				return "registerError";
			}
			
		}
	}
	
	@GetMapping("user/login.htm")
	public String showLoginForm(ModelMap model) {
		User user = new User(); // FormBackingObject
		model.addAttribute("user", user);
		return "loginUser";
	}
	
	@PostMapping("user/login.htm")
	public ModelAndView handleLogin(@ModelAttribute("user") User user, BindingResult result, SessionStatus status, UserDAO userdao, HttpSession session) throws AdvertException {
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("addUserForm");
			return model;
		} else {
			User retrivedUser = userdao.get(user);
			if(retrivedUser != null) {
				session.setAttribute("user", retrivedUser);
				status.setComplete();
				ModelAndView model = new ModelAndView("redirect:/home");
				return model;
			} else {
				ModelAndView model = new ModelAndView("loginError");
				return model;
			}
			
		}
	}
}
