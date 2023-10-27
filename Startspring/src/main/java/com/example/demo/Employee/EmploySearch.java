package com.example.demo.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmploySearch {
	@Autowired
	JdbcTemplate jt;

	public List<Map<String,Object>> search(Employee e) {
		String st="";
		int count=0;
		String fn=e.getFirstName();
		long pn=e.getPhoneNumber();
		String em=e.getEmail();
		if(fn!=null || pn!=0 || em!=null) {
			st=st+" where ";
		}if(fn!=null) {
			st=st+"  firstName like '"+fn+"%' ";
			count++;
		}if(pn!=0){
			if(pn!=0 && count>0) {
				st=st+" and  phoneNumber like '"+pn+"%' ";
				count++;
			}else{
				st=st+"   phoneNumber like '"+pn+"%' ";
				count++;
			}}if(em!=null) {
				if(em!=null && count>0) {
					st=st+"  and email like '"+em+"%' ";
				}else {
					st=st+"  email like '"+em+"%' ";
				}}
			String sql="select * from delegate   "+st;
			List<Map<String,Object>> table=new ArrayList<Map<String,Object>>();
			table=jt.queryForList(sql);
			return table;
	}
	
	public List<Map<String,Object>> specialSearch(Employee e) {
		String fn=e.getFirstName();
		long pn=e.getPhoneNumber();
		String em=e.getEmail();

		String sql="select * from delegate where firstName like '"+fn+"%' and phoneNumber like '"+pn+"%' and email like '"+em+"%'";
		List<Map<String,Object>> table=new ArrayList<Map<String,Object>>();
		table=jt.queryForList(sql);

		return table;
	}


}
