package com.example.demo.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	HashMap<String,String> hm=new HashMap<String,String>();

	@Autowired
	EmployeeService es;
	
	@Autowired
	EmploySearch esh;

	@PostMapping("insert")
	public String data_insertion(@RequestBody Employee e) {
		try{
			int result=es.dataInsert(e);
			if(result>0) {
				hm.put("successfull", "Data inserted successfully");
			}else {
				hm.put("UnSuccessfull", "Data Not inserted ");
			}}catch(Exception ex) {
				hm.put("error", ex.getMessage());
			}
		return hm+"";
	}

	@PostMapping("update")
	public String data_updation(@RequestBody Employee e) {
		try{
			int result=es.dataUpdate(e);
			if(result>0) {
				hm.put("successfull", "Data updated successfully");
			}else {
				hm.put("UnSuccessfull", "Data Not updated ");
			}}catch(Exception ex) {
				hm.put("error", ex.getMessage());
			}
		return hm+"";
	}
	@PostMapping("delete")
	public String data_deletion(@RequestBody Employee e) {
		try{
			int result=es.dataDelete(e);
			if(result>0) {
				hm.put("successfull", "Data Deleted successfully");
			}else {
				hm.put("UnSuccessfull", "Data Not updated ");
			}}catch(Exception ex) {
				hm.put("error", ex.getMessage());
			}
		return hm+"";
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return  es.getAllEmployees();
	}

	@GetMapping("/view")
	public List<Map<String,Object>> getEmployee() {
		return  es.view();
	}

	@GetMapping("/viewOne")
	public List<Map<String,Object>> getEmploye() {
		return es.viewOne();
	}
	
	@GetMapping("/viewById")
	public List<Map<String,Object>> getEmploy(@RequestBody Employee e) {
		return  es.viewById(e);
	}
	
	@GetMapping("/search")
	public List<Map<String,Object>> getEmplo(@RequestBody Employee e) {
		return esh.search(e);
	}
	
	@GetMapping("/specialsearch")
	public List<Map<String,Object>> getEmpl(@RequestBody Employee e) {
		return esh.specialSearch(e);
	}

}
