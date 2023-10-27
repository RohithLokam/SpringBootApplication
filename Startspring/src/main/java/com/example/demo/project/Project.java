package com.example.demo.project;

public class Project {
	
	private int empid;
	
	private String username;
	private String password;
	private String existingProjectId;
	private String newProjectName;
	private String projectName;
	private String description;
	private String startDate;
	private String endDate;
	
	private String requiredEmployes;
	private String projectManager;
	private String hr;
	private String assignedBy;
	
	
	public String getExistingProjectId() {
		return existingProjectId;
	}
	public void setExistingProjectId(String existingProjectId) {
		this.existingProjectId = existingProjectId;
	}
	public String getNewProjectName() {
		return newProjectName;
	}
	public void setNewProjectName(String newProjectName) {
		this.newProjectName = newProjectName;
	}
	
	
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRequiredEmployes() {
		return requiredEmployes;
	}
	public void setRequiredEmployes(String requiredEmployes) {
		this.requiredEmployes = requiredEmployes;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getHr() {
		return hr;
	}
	public void setHr(String hr) {
		this.hr = hr;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	

}
