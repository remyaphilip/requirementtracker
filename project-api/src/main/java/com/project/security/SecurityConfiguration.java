package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
//	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService);
//		.inMemoryAuthentication()
//		.withUser("user").password("pass").authorities("ROLE_USER")
//		.and()
//		.withUser("adminuser").password("password").authorities("ROLE_ADMIN");
	}

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	 http
	 .formLogin()
	 .loginProcessingUrl("/login")
//	 .failureForwardUrl("/loginfailure")
	 .permitAll().
	 and()
	 .authorizeRequests()
//	 .antMatchers("/login").permitAll()
	 .anyRequest()
	 .authenticated()
	 .and()
	 .logout().logoutUrl("/logout")
	 .invalidateHttpSession(true)
	 .and()
	 .csrf().disable();
	 }

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.httpBasic()
//		.and()
//			.authorizeRequests()
//			.antMatchers("/login")
//			.permitAll()
//			.anyRequest()
//			.authenticated()
//		.and()
//			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//
//	}

	 @Bean
	 public AuthenticationEntryPoint loginUrlauthenticationEntryPoint(){
	 return new LoginUrlAuthenticationEntryPoint("/login");
	 }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}