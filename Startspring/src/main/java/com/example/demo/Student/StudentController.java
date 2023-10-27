package com.example.demo.Student;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	StudentService ss;
	
	
	@PostMapping("student_data")
	public String data(@RequestBody Student s) {
		int result=ss.dataInsert(s);
		if(result>0) {
			return "Data inserted successfully";
		}else {
			return "Data not inserted";
		}
	}
	HashMap<String,String> hm=new HashMap<String,String>();
	@PostMapping("data")
	public String info(@RequestBody Student s) {
		try{
			int result=ss.dataInsert(s);
		if(result>0) {
			hm.put("successfull", "Data inserted successfully");
		}else {
			hm.put("UnSuccessfull", "Data Not inserted ");
		}}catch(Exception e) {
			hm.put("error", e.getMessage());
		}
		return hm+"";
	}
	@PostMapping("update")
	public String info1(@RequestBody Student s) {
		try{
			int result=ss.dataUpdate(s);
		if(result>0) {
			hm.put("successfull", "Data Updated successfully");
		}else {
			hm.put("UnSuccessfull", "Data Not Updated ");
		}}catch(Exception e) {
			hm.put("error", e.getMessage());
		}
		return hm+"";
	}
	@PostMapping("delete")
	public String info2(@RequestBody Student s) {
		try{
			int result=ss.dataDelete(s);
		if(result>0) {
			hm.put("successfull", "Data Deleted successfully");
		}else {
			hm.put("UnSuccessfull", "Data Not Deleted ");
		}}catch(Exception e) {
			hm.put("error", e.getMessage());
		}
		return hm+"";
	}
	
}
