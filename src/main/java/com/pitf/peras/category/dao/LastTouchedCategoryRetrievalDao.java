package com.pitf.peras.category.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.pitf.peras.category.dao.domain.LastTouchedCategoryViewEntity;

public interface LastTouchedCategoryRetrievalDao extends
		CrudRepository<LastTouchedCategoryViewEntity, Long>,
		JpaSpecificationExecutor<LastTouchedCategoryViewEntity> {

}
