package com.ats.ws;

//import java.util.HashMap;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.Produces;


import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PathVariable;

import com.ats.util.RestResponse;

@RestController
@RequestMapping("/account")
public interface AccountWS {


	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(value = "/login")
	@ResponseBody
	RestResponse login(@RequestParam("email") String email, @RequestParam("password") String password);


	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(value = "/registration", produces = "application/json;charset=UTF-8")
	RestResponse registration(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("fullname") String fullname);


	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(value = "/checkLogin", produces = "application/json;charset=UTF-8")
	RestResponse checkLogin(@RequestParam("accessToken") String accessToken);


	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(value = "/changePassword", produces = "application/json;charset=UTF-8")
	RestResponse changePassword(@RequestParam("id") int id, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword);


	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping(value = "/changeStatus", produces = "application/json;charset=UTF-8")
	RestResponse changeStatus(@RequestParam("id") int id,@RequestParam("newStatus") String newStatus);

	
}
