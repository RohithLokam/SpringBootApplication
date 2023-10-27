package com.example.demo.SearchData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataService {
	@Autowired
	JdbcTemplate jdbctemplate;
	public String searching(Data data) {
		String username=data.getUsername();
		String password=data.getPassword();
		String message="",user_role="",roleid="";
		String sql="select * from SearchData where username=? and password=?";
		List<Map<String,Object>> table=new ArrayList<Map<String,Object>>();
		table=jdbctemplate.queryForList(sql,username,password); 
		for(Map<String,Object> info:table) {
			user_role=(String)info.get("role");	
		}
		if(table.isEmpty()){
			message="invalid credientials";
		}
		else {
			try {
				if(user_role.contentEquals("A") || user_role.contentEquals("H")) {
					int empid=data.getEmpid();
					String firstname=data.getFirstname();
					String lastname=data.getLastname();
					long phonenumber=data.getPhonenumber();
					int zipcode=data.getZipcode();
					String department=data.getDepartment();
					String role=data.getRole();
					String dob=data.getDob();
					String joindate=data.getJoindate();
					String status="B";
					username=firstname.substring(0,1)+lastname+dob.substring(0,2)+dob.substring(3,5)+dob.substring(8,10);
					DateTimeFormatter dtf=DateTimeFormatter.ofPattern("hhmm");
					LocalDateTime ldt=LocalDateTime.now();
					String dat=dtf.format(ldt);
					password=Character.toUpperCase(firstname.charAt(0))+firstname.substring(1,4)
					+lastname.charAt(lastname.length()-3)+lastname.charAt(lastname.length()-2)
					+lastname.charAt(lastname.length()-1)+dat;
					String email=firstname.substring(0,1)+lastname+"@miraclesoft.com";
					String seequel="insert into SearchData values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					String sq="select * from Ids where rolee=?";
					table=jdbctemplate.queryForList(sq,role);
					for(Map<String,Object> info:table) {
						roleid=(String)info.get("roleId");
						System.out.println(roleid);
					}
					if(roleid==null || roleid=="") {
						message="role is invalid";
					}else {
						int i= jdbctemplate.update(seequel,empid,firstname,lastname,phonenumber,zipcode,department,roleid,dob,username,password,email,joindate,status);
						if(i>0) {
							message="Data inserted";
						}
					}	
				}else {
					message=" You Do Not have Access To Enter  Employ Data ";
				}
			}
			catch(Exception e) {
				message=e.getMessage();
			}

		}
		return message;
	}
}
