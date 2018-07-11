package com.project.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PromanAuthenticationProvider authProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
			.authenticationEntryPoint(requestInterceptor())
			.and()
			.formLogin()
			.loginProcessingUrl("/login")
			.successHandler(successHandler())
			.failureHandler((request, response, authentication) -> {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter()
					.write("".toCharArray());
				response.flushBuffer();
			})
			.permitAll()
			.and()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
				httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			})
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			 .and().cors()
			 .configurationSource(request -> new
			 CorsConfiguration()
			 .applyPermitDefaultValues())
			.and()
			.csrf()
			.disable();

	}

	private AuthenticationSuccessHandler successHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse httpServletResponse,
					Authentication arg2) throws IOException, ServletException {
				httpServletResponse.setStatus(HttpStatus.OK.value());
				String json = new ObjectMapper().writeValueAsString(arg2.getPrincipal());
				httpServletResponse.getWriter()
					.write(json);
				httpServletResponse.flushBuffer();

			}
		};
	}

	private AuthenticationEntryPoint requestInterceptor() {
		return new CustomAuthenticationEntryPoint();
	}

	class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			System.out.println(authException.getLocalizedMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}
	//
	// @Override
	// public void configure(WebSecurity web) throws Exception {
	// web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	// }

}