package com.cloud9.onboard.config;


import static com.cloud9.onboard.constants.SecurityConstants.LOGIN_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cloud9.onboard.authentication.service.UserDetailsServiceImpl;
import com.cloud9.onboard.security.filters.JWTAuthenticationFilter;
import com.cloud9.onboard.security.filters.JWTAuthorizationFilter;
import com.cloud9.onboard.security.providers.CustomDBAuthenticationProvider;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	  @Autowired
	  private CustomDBAuthenticationProvider authProvider;

	  private UserDetailsServiceImpl userDetailsService;
	  private BCryptPasswordEncoder bCryptPasswordEncoder;

	    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.userDetailsService = userDetailsService;
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
	        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_URL));
	        http.csrf().disable()
	                .cors().and()
	                .authorizeRequests()
	                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
	                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .addFilter(authenticationFilter)
	                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	   auth.authenticationProvider(authProvider);
	    }

	    @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("OPTIONS");
	        config.addAllowedMethod("GET");
	        config.addAllowedMethod("POST");
	        config.addAllowedMethod("PUT");
	        config.addAllowedMethod("DELETE");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
	  
	    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
}