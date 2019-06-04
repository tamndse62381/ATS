package com.ats.ws;

import java.util.HashMap;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.dto.AccountDTO;
import com.ats.util.RestResponse;



@RestController
@RequestMapping("/account")
public interface AccountWS {

	@POST
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@RequestMapping(value = "/login")
	@ResponseBody HashMap<String, Integer> checkLogin(@RequestParam("email") String email, @RequestParam("password") String password);

	@POST
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@RequestMapping(value = "/registration", produces = "application/json;charset=UTF-8")
	RestResponse registratrion(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("fullname") String fullname);

	
	
//	@PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
//	public @ResponseBody User register(@RequestBody User user) {
//        user = userRepository.insert(user);
//		return user ;
//	}

}
