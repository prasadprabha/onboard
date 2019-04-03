package com.cloud9.onboard.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud9.onboard.model.db.Role;
import com.cloud9.onboard.model.db.User;
import com.cloud9.onboard.repository.data.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
	        if (null != user) {
		        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		        for (Role role : user.getRoles()) {
		            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		        }
		        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	        } else {
	        	throw new UsernameNotFoundException("NAT FOUND");
	        }
        
        }
       
}