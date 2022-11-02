package com.me.advert;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.me.dao.CategoryDAO;
import com.me.exception.AdvertException;
import com.me.pojo.Category;

@Controller
public class CategoryController {
	@GetMapping("category/add.htm")
	public String showCategoryForm(ModelMap model) {
		Category category = new Category(); // FormBackingObject
		model.addAttribute("category", category);
		return "addCategoryForm";
	}

	@PostMapping("category/add.htm")
	public String handleCategoryForm(@ModelAttribute("category") Category category, BindingResult result, SessionStatus status, CategoryDAO categorydao) throws AdvertException {
		if (result.hasErrors())
			return "addCategoryForm";
		else {
			categorydao.create(category);
			status.setComplete();
			return "addedCategory";
		}
	}
}
