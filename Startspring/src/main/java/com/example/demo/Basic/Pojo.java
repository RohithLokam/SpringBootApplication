package com.example.demo.Basic;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Pojo {
	
private  int a;
private  int b;
public int getA() {
	return a;
}
public void setA(int a) {
	this.a = a;
}
public int getB() {
	return b;
}
public void setB(int b) {
	this.b = b;
}


}
