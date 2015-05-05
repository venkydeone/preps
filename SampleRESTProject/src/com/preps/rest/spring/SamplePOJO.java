package com.preps.rest.spring;

import org.springframework.stereotype.Component;


@Component
public class SamplePOJO {

	public SamplePOJO(){
		this.id=1;
		this.name = "test";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int id;
	private String name;
}
