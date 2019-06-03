package com.ats.ws;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.Produces;

import org.json.JSONException;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.dto.AccountDTO;



@RestController
@RequestMapping("/account")
public interface AccountWS {

	@POST
	@RequestMapping(value = "/login")
	@ResponseBody AccountDTO checkLogin(@RequestParam("email") String email, @RequestParam("password") String password) 
			throws JSONException;

	@POST
	@RequestMapping(value = "/registration")
	String registratrion(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("fullname") String fullname);
//	
//	@PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
//	public @ResponseBody User register(@RequestBody User user) {
//        user = userRepository.insert(user);
//		return user ;
//	}

}
