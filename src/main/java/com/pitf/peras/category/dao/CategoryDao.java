package com.pitf.peras.category.dao;

import org.springframework.data.repository.CrudRepository;

import com.pitf.peras.category.dao.domain.CategoryEntity;

public interface CategoryDao extends CrudRepository<CategoryEntity, Long> {

	Iterable<CategoryEntity> findByUserId(Long userId);

}
