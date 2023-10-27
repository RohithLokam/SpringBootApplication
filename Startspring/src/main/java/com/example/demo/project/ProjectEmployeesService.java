package com.example.demo.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProjectEmployeesService {

	@Autowired
	JdbcTemplate jdbctemplate;

	public String assignemployees(Project p) {
		String message="",user_name="",email="",joindate="",projectname="",projectid="",role="",status="";
		long phonenumber=0;
		int requiredEmployees=0;
		String username=p.getUsername();
		String password=p.getPassword();
		List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		String seequel="select * from SearchData where username=? and password=?";
		data=jdbctemplate.queryForList(seequel,username,password);
		try {
			if(data.size()>0) {
				int empid=p.getEmpid();
				String employDetailsquery="select * from SearchData where empid=? ";
				String projectdetailsquery="select * from project_details where project_manager=?";
				List<Map<String,Object>> employdetails=jdbctemplate.queryForList(employDetailsquery,empid);
				List<Map<String,Object>> projectdetails=jdbctemplate.queryForList(projectdetailsquery,username);
				if(employdetails.isEmpty()) {
					message="Invalid employ id";
				}
				else {
					if(projectdetails.isEmpty()) {
						message=" no project is assign to you ";
					}else {     
						for(Map<String,Object> map:employdetails) {
							user_name=(String)map.get("username");
							phonenumber=(long)map.get("phonenumber");
							email=(String)map.get("email");
							joindate=(String)map.get("joindate");
							role=(String)map.get("role");
							status=(String)map.get("status");
						}
						for(Map<String,Object> map:projectdetails) {
							projectname=(String)map.get("project_name");
							projectid=(String)map.get("project_id");
							requiredEmployees=(int)map.get("required_employees");
						}
						String sql="select * from project_employ_details where project_id=?";
						List<Map<String,Object>> count=new ArrayList<Map<String,Object>>();
						count=jdbctemplate.queryForList(sql,projectid);
						System.out.println(count.size());
						if(count.size()<requiredEmployees) {
							DateTimeFormatter datetimeformatter=DateTimeFormatter.ofPattern("dd/MM/YYYY");
							LocalDateTime localdatetime=LocalDateTime.now();
							String date=datetimeformatter.format(localdatetime);
							if((status.equalsIgnoreCase("B") && role.equalsIgnoreCase("E")) || (username==user_name)) {
								String insertquery="insert into project_employ_details values(?,?,?,?,?,?,?,?,?)";
								int i=jdbctemplate.update(insertquery,projectid,projectname,empid,user_name,phonenumber,email,joindate,date,username);
								String updatequery="update SearchData set status='A' where empid=? or username=?";
								jdbctemplate.update(updatequery,empid,username);

								if(i>0) {
									message="employ assigned";
								}else {
									message="employ not assigned";
								}
							}else {
								message="employ not available";
							}
						} else {
							message="There is no requirement for new employ";

						}
					}
				}
			}else {
				message="invalid credentials";
			}
		}catch(Exception e) {
			message=e.getMessage();
		}
		return message;
	}

}
