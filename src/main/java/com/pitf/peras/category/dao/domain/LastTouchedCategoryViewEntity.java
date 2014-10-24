package com.pitf.peras.category.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "v_last_touched_category")
public class LastTouchedCategoryViewEntity {
	@Id
	@Column(name = "category_id")
	private Long categoryId;
	@Column
	private String name;
	@Column(name = "last_touched")
	private Date doneDate;
	@Column(name = "danger")
	private Date dangerDate;
	@Column(name = "warning")
	private Date warningDate;

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

	public Date getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	public Date getDangerDate() {
		return dangerDate;
	}

	public void setDangerDate(Date dangerDate) {
		this.dangerDate = dangerDate;
	}

	public Date getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(Date warningDate) {
		this.warningDate = warningDate;
	}

}
