package com.cloud9.onboard.authentication.service;

public interface SecurityService {
	String findLoggedInUsername();
    void autologin(String username, String password);

}
