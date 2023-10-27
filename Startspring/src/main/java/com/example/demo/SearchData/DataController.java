package com.example.demo.SearchData;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("searchdata")
public class DataController {

	@Autowired
	DataService ds;

	@PostMapping("employ_data")
	public String searching(@RequestBody Data d) {
		return ds.searching(d);
	}

}
