package com.ats.ws;

import javax.ws.rs.POST;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.util.RestResponse;

@RestController
@RequestMapping("/employer")
public interface EmployerWS {
	@POST
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@RequestMapping(value = "/registration", produces = "application/json;charset=UTF-8")
	RestResponse registrationEmp(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("fullname") String fullname);
}
