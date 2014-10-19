package com.pitf.peras.category.dao.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.pitf.peras.task.dao.domain.TaskEntity;

@Entity(name = "t_category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_category_id")
	@SequenceGenerator(name = "seq_category_id", sequenceName = "seq_category_id")
	@Column(name = "category_id")
	private Long categoryId;
	@Column
	private String name;
	@Column(name = "user_id")
	private Long userId;
	@OneToMany(mappedBy = "categoryEntity")
	private Collection<TaskEntity> taskEntity;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Collection<TaskEntity> getTaskEntity() {
		return taskEntity;
	}

	public void setTaskEntity(Collection<TaskEntity> taskEntity) {
		this.taskEntity = taskEntity;
	}

}
