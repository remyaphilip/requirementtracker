package com.proman.api.project.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.proman.api.user.model.User;
import com.proman.api.user.repository.UserRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class PromanAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = new User();
		user.setEmail(username);
		user.setPasswordHash(password);
		Example<User> ex = Example.of(user);
		if (userRepository.exists(ex)) {
			List<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>();
			grantedAuth.add(new SimpleGrantedAuthority("ROLE_USER"));
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password,
					grantedAuth);
			return auth;
		} else {

			return null;
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
