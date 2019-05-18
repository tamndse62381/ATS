package com.ats.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public interface AccountWS {

	@POST
	@RequestMapping(value = "/login")
	String checkLogin(String username , String password);
	
	@GET
	@RequestMapping(value = "/registration")
	String registratrion(String username ,String password,int typeID,int status);
}
