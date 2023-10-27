package com.example.demo.Basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArithmeticController {
    @Autowired
	ArthimeticService bs;
	
	@GetMapping
	public String Display() {
		return "<h1>welcome</h1>";
	}
	
	@GetMapping("add/{a}/{b}")
	public String add(@PathVariable int a,@PathVariable int b) {
		int total=bs.add(a,b);
		return "Addition of "+a+" and "+b+" = "+total;
	}
	@PostMapping("sub")
	public String sub(@RequestParam int a,@RequestParam int b) {
		int total=bs.sub(a,b);
		return "Subtraction of "+a+" and "+b+" = "+total;
	}
	@PostMapping("mul")
	public String mul(@RequestBody Pojo p) {
		int total=bs.mul(p.getA(),p.getB());
		return "Multiplication value = "+total;
	}
	@PostMapping("div")
	public String div(@RequestBody Pojo p) {
		String ex="";
		try {
			ex="Division value = "+bs.div(p);
			
		}
		catch(Exception e) {
			ex=ex+e;
		}
		return ex;
	}
	
}