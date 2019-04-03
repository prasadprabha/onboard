package com.cloud9.onboard.security.filters;

import static com.cloud9.onboard.constants.SecurityConstants.HEADER_STRING;
import static com.cloud9.onboard.constants.SecurityConstants.SECRET;
import static com.cloud9.onboard.constants.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloud9.onboard.model.db.Role;
import com.cloud9.onboard.pojo.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
    private AuthenticationManager authenticationManager;
    
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
       
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	
			InputStream ist = req.getInputStream();
			res.setHeader("Access-Control-Allow-Headers", "Authorization");
			res.setHeader("Access-Control-Expose-Headers", "Authorization");
			logger.debug("Attempting for Authentication......");
            ApplicationUser creds = new ObjectMapper()
                    .readValue(ist, ApplicationUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
    	
    	logger.debug("Successfully authenticated....");
    	List<String> roles = new ArrayList<String>();
    	
    	for (GrantedAuthority authority : auth.getAuthorities() ) {
    		Role role = (Role) authority;
    		roles.add(role.getName());
    	}
    	
    	ApplicationUser loggedInUser = (ApplicationUser) auth.getDetails();
    	loggedInUser.setEmail(((User) auth.getPrincipal()).getUsername());
    	loggedInUser.setRoles(roles);
    	logger.debug("Creating jwt token for the user");
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .claim("id", "0")
                .claim("email", ((User) auth.getPrincipal()).getUsername())
                .claim("firstName", loggedInUser.getFirstName())
                .claim("lastName", loggedInUser.getLastName())
                .claim("roles", roles)
                .claim("zoneid", loggedInUser.getZoneId())
                .compact();
        
        logger.debug("Appending token in the response.");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("token", token);
        JSONObject jsonObject = new JSONObject(jsonMap);
        res.getWriter().write(jsonObject.toString());
    }
    
    @Override
    public void setPasswordParameter(String passwordParameter) {
    	super.setPasswordParameter("password");
    }
    
    
    @Override
    public void setUsernameParameter(String usernameParameter) {
    	super.setUsernameParameter("email");
    }
    
    
}