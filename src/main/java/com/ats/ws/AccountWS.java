package com.ats.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public interface AccountWS {

	@POST
	@RequestMapping(value = "/login")
	String checkLogin(@RequestParam("email") String name, @RequestParam("password") String password);

	@GET
	@RequestMapping(value = "/registration")
	String registratrion(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("fullname") String fullname);

}
