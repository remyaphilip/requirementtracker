package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private PromanAuthenticationProvider authenticationProvider;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider);
	}

//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//		.withUser("test2@email.com").password("test").roles("Admin");
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//httpSecurity.addFilterBefore(new CorsFilter(null),ChannelProcessingFilter.class);
		httpSecurity.authorizeRequests().anyRequest()
		//.permitAll()
		.fullyAuthenticated()
		.and().httpBasic();
		httpSecurity.csrf().disable();
	}

	
}
