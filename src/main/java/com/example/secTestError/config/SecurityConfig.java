package com.example.secTestError.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${some.prop}")
	private String someProp;

	@Autowired
//	@Lazy //<-- uncomment this line and the test works!
	private MyAuthSuccessHandler myAuthSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http
		.authorizeRequests()
		.anyRequest().permitAll()
		.and()
			.formLogin().permitAll()
		.and()
			.logout().permitAll()
		.and()
			.csrf().disable();
	}
	
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth
			.inMemoryAuthentication()
				.withUser("test")
				.password("password")
				.authorities("ADMIN").roles("ADMIN")
				.and()
				.withUser("noperms")
				.password("password")
				.roles();;
		}
	}
}
