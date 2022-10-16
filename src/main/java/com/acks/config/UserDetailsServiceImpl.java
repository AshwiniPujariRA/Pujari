package com.acks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.acks.model.User5;
import com.acks.userrepository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	 @Autowired
	private UserRepository userRepository;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User5 user=userRepository.getUserByName(username);
	
	if(user==null)
	{
		throw new UsernameNotFoundException("could not found User !!");
		
				
	}

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return  customUserDetails;
	}

}
