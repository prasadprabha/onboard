package com.cloud9.onboard.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud9.onboard.authentication.service.SecurityService;
import com.cloud9.onboard.authentication.service.UserService;
import com.cloud9.onboard.model.db.Role;
import com.cloud9.onboard.model.db.User;
import com.cloud9.onboard.response.LoginResponse;
import static com.cloud9.onboard.constants.SecurityConstants.HEADER_STRING;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;
	
	@GetMapping("/students/{studentId}/courses")
	public List<String> retrieveCoursesForStudent(@PathVariable String studentId) {
		List<String> courseList = new ArrayList<>();
		courseList.add("Java");
		return courseList;
	}
	
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(User user,HttpServletResponse response) {
		String jwtToken = response.getHeader(HEADER_STRING);
		LoginResponse loginResponse = new LoginResponse();
		LoginResponse.Data data = loginResponse.new Data();
		data.setToken(jwtToken);
		loginResponse.setData(data);
		return loginResponse;
    }
	
	
	/**
	 * Logout
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<LoginResponse> logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SecurityContextHolder.clearContext();
        HttpSession session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        if (null != request.getCookies()) {
        	for(Cookie cookie : request.getCookies()) {
            	cookie.setMaxAge(0);
        	}
        }
        
        response.addHeader(HEADER_STRING,"");
        LoginResponse authResult = new LoginResponse();
		return new ResponseEntity<LoginResponse>(authResult, HttpStatus.OK);
	}
	
	
	   @PostMapping("/sign-up")
	    public void signUp(@RequestBody User user) {
		   Role admin = new Role();
		   admin.setName("Admin");
		   admin.setId(1L);
		   Set<Role> roles = new HashSet<Role>();
		   roles.add(admin);
		   user.setRoles(roles);
		   userService.save(user);
	    }
	   
	   
		@RequestMapping(value = "/get-application-name", method = RequestMethod.GET)
	    public String getApplicationName() {
			
	        return "Saved the user";
	    }

}
