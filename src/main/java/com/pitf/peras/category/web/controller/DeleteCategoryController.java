package com.pitf.peras.category.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.category.facade.CategoryFacade;
import com.pitf.peras.category.web.domain.DeleteCategoryRequest;

@RestController
public class DeleteCategoryController {
	private CategoryFacade categoryFacade;

	@Autowired
	public DeleteCategoryController(CategoryFacade categoryFacade) {
		super();
		this.categoryFacade = categoryFacade;
	}

	@RequestMapping("${deleteCategoryController}")
	public String createCategory(DeleteCategoryRequest deleteCategoryRequest) {
		categoryFacade.deleteCategory(deleteCategoryRequest.getCategoryId());
		return "true";
	}
}
