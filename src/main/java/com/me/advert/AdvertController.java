package com.me.advert;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.me.dao.AdvertDAO;
import com.me.dao.CategoryDAO;
import com.me.dao.UserDAO;
import com.me.exception.AdvertException;
import com.me.pojo.Advert;
import com.me.pojo.Category;
import com.me.pojo.User;

@Controller
public class AdvertController {
	@GetMapping("advert/add.htm")
	public String showAdvertForm(ModelMap model, CategoryDAO categorydao) throws AdvertException {
		Advert advert = new Advert(); // FormBackingObject
		List<Category> categories = categorydao.list();
		model.addAttribute("categories", categories);
		model.addAttribute("advert", advert);
		return "addAdvertForm";
	}

	@PostMapping("advert/add.htm")
	public String handleAdvertForm(HttpSession session, @ModelAttribute("advert") Advert advert, BindingResult result, SessionStatus status, AdvertDAO advertdao, UserDAO userdao, CategoryDAO categorydao) throws AdvertException {
		if (result.hasErrors())
			return "addAdvertForm";
		else {
			
			User user = (User) session.getAttribute("user");
			Category category = categorydao.get(advert.getCategory());
			advertdao.create(advert.getTitle(), advert.getMessage(), user, category);
			status.setComplete();
			return "addedAdvert";
		}
	}
	
	@GetMapping("advert/list.htm")
	public String listAdvert(ModelMap model, AdvertDAO advertdao) throws AdvertException {
		List<Advert> adverts = advertdao.list();
		model.addAttribute("adverts", adverts);
		return "viewAdvert";
	}
}