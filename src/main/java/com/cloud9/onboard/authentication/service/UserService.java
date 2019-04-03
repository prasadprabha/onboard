package com.cloud9.onboard.authentication.service;

import java.util.List;

import com.cloud9.onboard.model.db.Role;
import com.cloud9.onboard.model.db.User;

public interface UserService {
	
    void save(User user);
    
    User findByUsername(String username);
    
	void saveRole(Role role);
	
	List<Role> getRoles(String username);
}