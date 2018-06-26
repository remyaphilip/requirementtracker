package com.project.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.issuetrack.model.Issue;
import com.user.model.User;
import com.user.repository.UserRepository;

public class PromanUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setEmail(username);
		Example<User> ex1 = Example.of(user);
		user = userRepository.findOne(ex1);
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add(new SimpleGrantedAuthority(user.getUserRole()));
		UserDetails userDetails = (UserDetails)new User(user.getEmail(),user.getPasswordHash(), authority);

		return userDetails;
	}

}
