package com.pitf.peras.category.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.pitf.peras.category.dao.domain.CategoryEntity;

public interface CategoryRetrievalDao extends
		CrudRepository<CategoryEntity, Long>,
		JpaSpecificationExecutor<CategoryEntity> {

}
