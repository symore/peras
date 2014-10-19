package com.pitf.peras.category.web.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pitf.peras.category.domain.Category;
import com.pitf.peras.category.web.domain.CategoryView;

@Component
public class CategoryTransformer {
	public List<CategoryView> transformCategories(List<Category> categoryList) {
		List<CategoryView> result = new ArrayList<>();
		for (Category category : categoryList) {
			result.add(transformCategory(category));
		}
		return result;
	}

	public CategoryView transformCategory(Category category) {
		return new CategoryView(category.getName(), category.getCategoryId());
	}
}
