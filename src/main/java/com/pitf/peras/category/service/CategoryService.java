package com.pitf.peras.category.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pitf.peras.category.dao.CategoryDao;
import com.pitf.peras.category.dao.CategoryRetrievalDao;
import com.pitf.peras.category.dao.CategorySpecifications;
import com.pitf.peras.category.dao.domain.CategoryEntity;
import com.pitf.peras.category.domain.Category;
import com.pitf.peras.category.domain.CategoryRetrievalParameters;
import com.pitf.peras.category.domain.CategoryStatus;

@Component
public class CategoryService {
	private CategoryDao categoryDao;
	private CategoryRetrievalDao categoryRetrievalDao;

	@Autowired
	public CategoryService(CategoryDao categoryDao,
			CategoryRetrievalDao categoryRetrievalDao) {
		super();
		this.categoryDao = categoryDao;
		this.categoryRetrievalDao = categoryRetrievalDao;
	}

	@Transactional
	public Category createCategory(Category category) {
		return createCategory(categoryDao.save(createCategoryEntity(category)));
	}

	private Category createCategory(CategoryEntity categoryEntity) {
		Category result = new Category();
		result.setCategoryId(categoryEntity.getCategoryId());
		result.setName(categoryEntity.getName());
		result.setUserId(categoryEntity.getUserId());
		result.setWarningThreshold(categoryEntity.getWarningThreshold());
		result.setDangerThreshold(categoryEntity.getDangerThreshold());
		return result;
	}

	private CategoryEntity createCategoryEntity(Category category) {
		CategoryEntity result = new CategoryEntity();
		result.setCategoryId(category.getCategoryId());
		result.setName(category.getName());
		result.setUserId(category.getUserId());
		result.setWarningThreshold(category.getWarningThreshold());
		result.setDangerThreshold(category.getDangerThreshold());
		return result;
	}

	public List<Category> listCategories(Long userId) {
		List<Category> result = new ArrayList<Category>();
		Iterable<CategoryEntity> categoryEntities = categoryDao
				.findByUserId(userId);

		CategoryRetrievalParameters categoryRetrievalParameters = new CategoryRetrievalParameters(
				userId);
		Map<Long, CategoryEntity> alertCategories = getAlertCategories(categoryRetrievalParameters);
		Map<Long, CategoryEntity> warningCategories = findWarningCategories(categoryRetrievalParameters);

		for (CategoryEntity categoryEntity : categoryEntities) {
			Category category = createCategory(categoryEntity);
			if (alertCategories.containsKey(category.getCategoryId())) {
				category.setCategoryStatus(CategoryStatus.ALERT);
				category.setMessages(Arrays
						.asList("You have tasks to do in 1 weeks"));
			} else if (warningCategories.containsKey(category.getCategoryId())) {
				category.setCategoryStatus(CategoryStatus.WARNING);
				category.setMessages(Arrays
						.asList("You have %d tasks to do in 2 week"));
			} else {
				category.setCategoryStatus(CategoryStatus.NICE);
			}
			result.add(category);
		}
		return result;
	}

	private Map<Long, CategoryEntity> findWarningCategories(
			CategoryRetrievalParameters categoryRetrievalParameters) {
		List<CategoryEntity> categories = categoryRetrievalDao
				.findAll(new CategorySpecifications()
						.findWarningCategories(categoryRetrievalParameters));
		return mapCategories(categories);
	}

	private Map<Long, CategoryEntity> getAlertCategories(
			CategoryRetrievalParameters categoryRetrievalParameters) {
		List<CategoryEntity> categories = categoryRetrievalDao
				.findAll(new CategorySpecifications()
						.findAlertCategories(categoryRetrievalParameters));
		Map<Long, CategoryEntity> result = mapCategories(categories);
		return result;
	}

	private Map<Long, CategoryEntity> mapCategories(
			List<CategoryEntity> categories) {
		Map<Long, CategoryEntity> result = new HashMap<Long, CategoryEntity>();
		for (CategoryEntity category : categories) {
			result.put(category.getCategoryId(), category);
		}
		return result;
	}

	public void deleteCategory(Long categoryId) {
		categoryDao.delete(categoryId);
	}
}
