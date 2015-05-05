package com.preps.rest.spring;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("request")
public class SampleRestController {
	
	@Inject
	private SamplePOJO pojo;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response getResponse(){
		return Response.ok().entity(pojo).build();
	}
	
	@RequestMapping(value="modified", method=RequestMethod.GET)
	@ResponseBody
	public Response getModResponse(){
		pojo = new SamplePOJO();
		pojo.setId(123);
		pojo.setName("modified");
		return Response.ok().entity(pojo).build();
	}
}
