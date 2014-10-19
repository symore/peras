package com.pitf.peras.task.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pitf.peras.category.domain.Category;
import com.pitf.peras.category.facade.CategoryFacade;
import com.pitf.peras.category.web.domain.CategoryView;
import com.pitf.peras.task.web.domain.ManageTasksModel;

@Controller
public class ManageTasksController {
	@Value("${manageTasksController.successView}")
	private String successView;
	private CategoryFacade categoryFacade;

	@Autowired
	public ManageTasksController(CategoryFacade categoryFacade) {
		super();
		this.categoryFacade = categoryFacade;
	}

	@ModelAttribute("ManageTasksModel")
	public ManageTasksModel initManageTasksModel() {
		List<Category> categoryList = categoryFacade.listCategories();
		return new ManageTasksModel(transformCategories(categoryList));
	}

	private List<CategoryView> transformCategories(List<Category> categoryList) {
		List<CategoryView> result = new ArrayList<CategoryView>();
		for (Category category : categoryList) {
			result.add(new CategoryView(category.getName(), category
					.getCategoryId()));
		}
		return result;
	}

	@RequestMapping("${manageTasksController}")
	public String taskHome() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

}
