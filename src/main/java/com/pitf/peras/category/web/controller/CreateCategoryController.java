package com.pitf.peras.category.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.category.domain.Category;
import com.pitf.peras.category.facade.CategoryFacade;
import com.pitf.peras.category.web.domain.CategoryView;
import com.pitf.peras.category.web.domain.CreateCategoryRequest;

@RestController
public class CreateCategoryController {
	private CategoryFacade categoryFacade;

	@Autowired
	public CreateCategoryController(CategoryFacade categoryFacade) {
		super();
		this.categoryFacade = categoryFacade;
	}

	@RequestMapping("${createCategoryController}")
	public CategoryView createCategory(
			CreateCategoryRequest createCategoryRequest) {
		return transformCategory(categoryFacade
				.createCategory(transformCategory(createCategoryRequest)));
	}

	private CategoryView transformCategory(Category category) {
		return new CategoryView(category.getName(), category.getCategoryId());
	}

	private Category transformCategory(
			CreateCategoryRequest createCategoryRequest) {
		Category result = new Category();
		result.setName(createCategoryRequest.getName());
		result.setDangerThreshold(createCategoryRequest.getDangerThreshold());
		result.setWarningThreshold(createCategoryRequest.getWarningThreshold());
		return result;
	}
}
