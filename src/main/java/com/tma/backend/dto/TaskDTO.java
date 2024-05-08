package com.tma.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_tasks") // change it as per local DB structure
public class TaskDTO {
	
	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long task_id;
	
	@Column(name = "title")
    private String title;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "completed")
    private String completed;
	
	@Column(name = "created_dtm")
    private LocalDateTime created_dtm;
	
	@Column(name = "updated_dtm")
    private LocalDateTime updated_dtm;
	
	public Long getTaskID() {
		return task_id;
	}
	
	public void setTaskID(Long task_id) {
		this.task_id = task_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCompleted() {
		return completed;
	}
	
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	
	public void setCreatedDTM(LocalDateTime created_dtm) {
		this.created_dtm = created_dtm;
	}
    
    public void setUpdatedDTM(LocalDateTime updated_dtm) {
		this.updated_dtm = updated_dtm;
	}
}
