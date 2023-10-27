package com.example.demo.Basic;

import org.springframework.stereotype.Service;

@Service
public class ArthimeticService {

	public int add(int a, int b) {
		return a+b;
	}

	public int sub(int a, int b) {
		return a-b;
	}

	public int mul(int a,int b) {
		return a*b;
	}

	public int div(Pojo p) {
		return p.getA()/p.getB();
	}

	

	
}
