package com.example.demo.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	@Autowired
	JdbcTemplate jdbctemplate;

	public String creating(Project project) {
		String message="",managerStatus="",hrStatus="";
		String username=project.getUsername();
		String password=project.getPassword();
		List<Map<String,Object>> employdetails=new ArrayList<Map<String,Object>>();
		String seequel="select * from SearchData where username=? and password=? and role='A' ";
		employdetails=jdbctemplate.queryForList(seequel,username,password);
		if(employdetails.isEmpty()) {
			message="invalid credentials";
		}else {
			try {
				String projectName=project.getProjectName();
				String description=project.getDescription();
				String startDate=project.getStartDate();
				String endDate=project.getEndDate();
				String requiredEmployes=project.getRequiredEmployes();
				String projectManager=project.getProjectManager();
				String hr=project.getHr();
				String projectId=projectName+startDate.substring(0,2)+startDate.substring(3,5)+startDate.substring(6,10);
				String assignedBy=username;

				String projectmanagerdetailsquery="select * from SearchData where username=? and role='P' ";
				String hrdetailsquery="select * from SearchData where username=? and role='H' ";
				List<Map<String,Object>> projectmanagerdetails=new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> hrdetails=new ArrayList<Map<String,Object>>();

				projectmanagerdetails=jdbctemplate.queryForList(projectmanagerdetailsquery,projectManager);
				hrdetails=jdbctemplate.queryForList(hrdetailsquery,hr);

				if(projectmanagerdetails.isEmpty()) {
					message=" Invalid project manager details.  \n";
				}if(hrdetails.isEmpty()) {
					message=message+" Invalid hr details. ";
				}if(!projectmanagerdetails.isEmpty() && !hrdetails.isEmpty()) {

					for(Map<String,Object> map:projectmanagerdetails) {
						managerStatus=(String)map.get("status");
					}
					for(Map<String,Object> map:hrdetails) {
						hrStatus=(String)map.get("status");
					}
					if(managerStatus.equalsIgnoreCase("A") ) {
						message="project manager currently not available  \n";
					}if(hrStatus.equalsIgnoreCase("A")) {
						message=message+"hr currently not available ";
					}if(managerStatus.equalsIgnoreCase("B") && hrStatus.equalsIgnoreCase("B")) {
						String insertquery="insert into project_details values(?,?,?,?,?,?,?,?,?)";
						int i=jdbctemplate.update(insertquery,projectId,projectName,description,startDate,endDate,requiredEmployes,projectManager,hr,assignedBy);
						String updatequery="update SearchData set status='A' where username=? or username=?";
						jdbctemplate.update(updatequery,projectManager,hr);			
						if(i>0) {
							message="project created successfully";
						}else {
							message="project not created";
						}	
					}else {
						message="manager/hr not available";
					}
				}
			}
			catch(Exception exception) {
				message=exception.getMessage();
			}
		}
		return message;
	}
}
