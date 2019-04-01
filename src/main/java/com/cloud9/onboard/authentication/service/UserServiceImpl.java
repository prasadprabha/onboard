package com.cloud9.onboard.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud9.onboard.model.db.Role;
import com.cloud9.onboard.model.db.User;
import com.cloud9.onboard.repository.data.RoleRepository;
import com.cloud9.onboard.repository.data.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
     //   user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }
    
    @Override
    public void saveRole(Role role) {
    	roleRepository.save(role);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public List<Role> getRoles(String username) {
    	//roleRepository.fi
    	Role admin = new Role();
    	List<Role> roles = new ArrayList<Role>();
    	return roles;
    	
    }
    
    
}