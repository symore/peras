package com.pitf.peras.task.domain;

public class RewiredTask {
	private Long oldPrevious;
	private Long oldNext;
	private Long newPrevious;
	private Long newNext;
	private Long rewiredTask;

	public Long getOldPrevious() {
		return oldPrevious;
	}

	public void setOldPrevious(Long oldPrevious) {
		this.oldPrevious = oldPrevious;
	}

	public Long getOldNext() {
		return oldNext;
	}

	public void setOldNext(Long oldNext) {
		this.oldNext = oldNext;
	}

	public Long getNewPrevious() {
		return newPrevious;
	}

	public void setNewPrevious(Long newPrevious) {
		this.newPrevious = newPrevious;
	}

	public Long getNewNext() {
		return newNext;
	}

	public void setNewNext(Long newNext) {
		this.newNext = newNext;
	}

	public Long getRewiredTask() {
		return rewiredTask;
	}

	public void setRewiredTask(Long rewiredTask) {
		this.rewiredTask = rewiredTask;
	}

}
