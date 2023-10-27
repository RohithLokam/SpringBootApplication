package com.example.demo.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	static String username="",password="",email="";
	@Autowired
	JdbcTemplate jt;
	@Autowired
	NamedParameterJdbcTemplate nt;
	public int dataInsert(Employee e) {
		int empid=e.getEmpid();
		String firstname=e.getFirstName();
		String lastname=e.getLastName();
		long phonenumber=e.getPhoneNumber();
		int zipcode=e.getZipCode();
		String department=e.getDepartment();
		String role=e.getRole();
		String dob=e.getDob();
		username=firstname.substring(0,1)+lastname+dob.substring(0,2)+dob.substring(3,5)+dob.substring(8,10);
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("hhmm");
		LocalDateTime ldt=LocalDateTime.now();
		String dat=dtf.format(ldt);
		password=Character.toUpperCase(firstname.charAt(0))+firstname.substring(1,4)
		+lastname.charAt(lastname.length()-3)+lastname.charAt(lastname.length()-2)
		+lastname.charAt(lastname.length()-1)+dat;
		email=firstname.substring(0,1)+lastname+"@miraclesoft.com";

		String sql="insert into delegate values(?,?,?,?,?,?,?,?,?,?,?)";

		return jt.update(sql,empid,firstname,lastname,phonenumber,zipcode,department,role,dob,username,password,email);
	}

	public int dataUpdate(Employee e) {				
		username=e.getFirstName().substring(0,1)+e.getLastName()+e.getDob().substring(0,2)
				+e.getDob().substring(3,5)
				+e.getDob().substring(8,10);
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("hhmm");
		LocalDateTime ldt=LocalDateTime.now();
		String dat=dtf.format(ldt);
		password=Character.toUpperCase(e.getFirstName().charAt(0))
				+e.getFirstName().substring(1,4)
				+e.getLastName().charAt(e.getLastName().length()-3)
				+e.getLastName().charAt(e.getLastName().length()-2)
				+e.getLastName().charAt(e.getLastName().length()-1)+dat;
		email=e.getFirstName().substring(0,1)+e.getLastName()+"@miraclesoft.com";
		MapSqlParameterSource mparam=new MapSqlParameterSource()
				.addValue("empid",e.getEmpid()).addValue("fn", e.getFirstName())
				.addValue("ln", e.getLastName()).addValue("pn", e.getPhoneNumber())
				.addValue("zc", e.getZipCode()).addValue("dt", e.getDepartment())
				.addValue("rl", e.getRole())
				.addValue("dob", e.getDob()).addValue("un", username)
				.addValue("pd", password).addValue("el", email);
		String sql="update delegate set firstname=(:fn),lastname=(:ln),phonenumber=(:pn),zipcode=(:zc),"
				+ "department=(:dt),role=(:rl),dob=(:dob),username=(:un),password=(:pd),email=(:el) where empid=(:empid)";
		return nt.update(sql, mparam);

	}
	public int dataDelete(Employee e) {
		MapSqlParameterSource mparam=new MapSqlParameterSource().addValue("empid",e.getEmpid());
		String sql="delete from delegate where empid=(:empid)";
		return nt.update(sql, mparam);
	}

	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM delegate";
		List<Map<String, Object>> rows = jt.queryForList(sql);
		List<Employee> employees = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			Employee employee = new Employee();
			employee.setEmpid((int) row.get("empid"));
			employee.setFirstName((String) row.get("firstName"));
			employee.setFirstName((String) row.get("lastName"));
			employees.add(employee);
		}
		return employees;
	}
	public List<Map<String,Object>> view() {
		String sql="select * from delegate";
		List<Map<String,Object>> table=new ArrayList<Map<String,Object>>();
		table=jt.queryForList(sql);

		return table;
	}
	public List<Map<String,Object>> viewOne() {
		String sql="select * from delegate";
		List<Map<String,Object>> result=new ArrayList<>();
		List<Map<String,Object>> table=new ArrayList<Map<String,Object>>();
		table=jt.queryForList(sql);
		for(Map<String,Object> data:table) {
			Map<String, Object> hm =new HashMap<String, Object>();
			hm.put("id", data.get("empid"));
			hm.put("firstName", data.get("firstName"));
			hm.put("lastName", data.get("lastName"));
			hm.put("phonenumber", data.get("phoneNumber"));
			hm.put("zipcode", data.get("zipCode"));
			hm.put("department", data.get("department"));
			hm.put("role", data.get("role"));
			hm.put("dob", data.get("dob"));
			hm.put("username", data.get("username"));
			hm.put("password", data.get("password"));
			hm.put("email", data.get("email"));
			result.add(hm);
			System.out.println(hm);
		}
		return result;
	}
	public List<Map<String,Object>> viewById(Employee e) {
		int id=e.getEmpid();
		String sql="select * from delegate where empid=?";
		List<Map<String,Object>> result=new ArrayList<>();
		List<Map<String,Object>> table=new ArrayList<Map<String,Object>>();
		table=jt.queryForList(sql,id);
		for(Map<String,Object> data:table) {
			Map<String, Object> hm =new HashMap<String, Object>();
			hm.put("id", data.get("empid"));
			hm.put("firstName", data.get("firstName"));
			hm.put("lastName", data.get("lastName"));
			hm.put("phonenumber", data.get("phoneNumber"));
			hm.put("zipcode", data.get("zipCode"));
			hm.put("department", data.get("department"));
			hm.put("role", data.get("role"));
			hm.put("dob", data.get("dob"));
			hm.put("username", data.get("username"));
			hm.put("password", data.get("password"));
			hm.put("email", data.get("email"));
			result.add(hm);
		}
		return table;
	}




}
