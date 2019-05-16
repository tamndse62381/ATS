package com.ats.ws;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AccountWS {

	@POST
	@RequestMapping(value = "/account/login")
	String checkLogin(String username , String password);
	
	@GET
	@RequestMapping(value = "/account/registration")
	String registratrion(String username ,String password,int typeID,int status);
}
