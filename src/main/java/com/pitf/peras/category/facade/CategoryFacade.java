package com.pitf.peras.category.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pitf.peras.category.domain.Category;
import com.pitf.peras.category.service.CategoryService;
import com.pitf.peras.security.domain.User;
import com.pitf.peras.security.service.AuthenticationService;

@Component
public class CategoryFacade {
	private CategoryService categoryService;
	private AuthenticationService authenticationService;

	@Autowired
	public CategoryFacade(CategoryService categoryService,
			AuthenticationService authenticationService) {
		super();
		this.categoryService = categoryService;
		this.authenticationService = authenticationService;
	}

	public Category createCategory(Category category) {
		User currentUser = getCurrentUser();
		category.setUserId(currentUser.getUserId());
		return categoryService.createCategory(category);
	}

	private User getCurrentUser() {
		return authenticationService.getCurrentUser();
	}

	public void deleteCategory(Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}

	public List<Category> listCategories() {
		User currentUser = getCurrentUser();
		return categoryService.listCategories(currentUser.getUserId());
	}
}
