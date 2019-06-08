package com.ats.ws;

import javax.ws.rs.POST;

import com.ats.dto.AccountDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.ats.util.RestResponse;

import java.util.Date;

@RestController
@RequestMapping("/employer")
public class EmployerWS {

	private static final Logger LOGGER = LogManager.getLogger(EmployerWS.class);


	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping(value = "/registration", produces = "application/json;charset=UTF-8")
	RestResponse registrationEmp(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("fullname") String fullname){
		LOGGER.info("Begin registrationEmp in EmployerWS with email - password - fullname: {}",
				email + " - " + password + " - " + fullname);
		int result;
		try {

			LOGGER.info("End registrationEmp in EmployerWS with email - password - fullname: {}",
					email + " - " + password + " - " + fullname);

				return new RestResponse(true, "Create To Successful", null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RestResponse(false, "Fail Create To Account", null);
	}
}
