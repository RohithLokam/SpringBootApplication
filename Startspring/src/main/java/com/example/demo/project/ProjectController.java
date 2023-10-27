package com.example.demo.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
public class ProjectController {
	
	@Autowired
	ProjectService projectservice;
	@Autowired
	ProjectEmployeesService projectemployservice;
	@Autowired
	ProjectCopyService projectcopyservice;
	
	@PostMapping("creating_project")
	public String creation(@RequestBody Project project) {
		return projectservice.creating(project);
		
	}
	
	@PostMapping("assign_employees")
	public String assigning(@RequestBody Project project) {
		return projectemployservice.assignemployees(project);
	}
	
	@PostMapping("project_clone")
	public String copying(@RequestBody Project project) {
		return projectcopyservice.projectcopy(project);
	}
	
	

}
