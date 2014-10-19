package com.pitf.peras.category.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitf.peras.category.domain.Category;
import com.pitf.peras.category.facade.CategoryFacade;
import com.pitf.peras.category.web.domain.CategoryView;
import com.pitf.peras.category.web.transformer.CategoryTransformer;

@RestController
public class ListCategoriesController {
	private CategoryFacade categoryFacade;
	private CategoryTransformer categoryTransformer;

	@Autowired
	public ListCategoriesController(CategoryFacade categoryFacade,
			CategoryTransformer categoryTransformer) {
		super();
		this.categoryFacade = categoryFacade;
		this.categoryTransformer = categoryTransformer;
	}

	@RequestMapping("${listCategoriesController}")
	public List<CategoryView> categoryList() {
		return transformCategories(categoryFacade.listCategories());
	}

	private List<CategoryView> transformCategories(List<Category> categoryList) {
		return categoryTransformer.transformCategories(categoryList);
	}

}
