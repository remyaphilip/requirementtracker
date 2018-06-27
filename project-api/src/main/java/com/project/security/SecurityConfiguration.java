package com.project.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PromanAuthenticationProvider authProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider).userDetailsService(super.userDetailsService());
		// .inMemoryAuthentication()
		// .withUser("user").password("pass").authorities("ROLE_USER")
		// .and()
		// .withUser("adminuser").password("password").authorities("ROLE_ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginProcessingUrl("/login").successHandler(successHandler())
				// .failureForwardUrl("/loginfailure")
				.permitAll().and().authorizeRequests()
				// .antMatchers("/login").permitAll()
				.anyRequest().authenticated().and().logout().logoutUrl("/logout").invalidateHttpSession(true).and()
				.csrf().disable();

		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

	}

	private AuthenticationSuccessHandler successHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse httpServletResponse,
					Authentication arg2) throws IOException, ServletException {
				httpServletResponse.setStatus(HttpStatus.OK.value());
				String json = new ObjectMapper().writeValueAsString(arg2.getPrincipal());
				httpServletResponse.getWriter().write(json);
				httpServletResponse.flushBuffer();

			}
		};
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http
	// .httpBasic()
	// .and()
	// .authorizeRequests()
	// .antMatchers("/login")
	// .permitAll()
	// .anyRequest()
	// .authenticated()
	// .and()
	// .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	//
	// }

	// @Bean
	// public AuthenticationEntryPoint loginUrlauthenticationEntryPoint(){
	// return new LoginUrlAuthenticationEntryPoint("/login");
	// }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}