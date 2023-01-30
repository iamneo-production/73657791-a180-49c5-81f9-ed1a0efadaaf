package com.examly.springapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.examly.springapp.security.service.UserDetailsServiceImpl;
import com.examly.springapp.security.service.jwt.AuthEntryPointJwt;
import com.examly.springapp.security.service.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		
		prePostEnabled = true)
//PURPOSE: Binds different filters that are used in an application
public class WebSecurityConfig{// extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	// @Override
	// public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	// 	authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	// }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

	 authProvider.setUserDetailsService(userDetailsService);//fetches user details to compare with user credentials
	 authProvider.setPasswordEncoder(passwordEncoder());//encode and decode password

	 return authProvider;
	 }

	// @Bean
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	// 	return super.authenticationManagerBean();
	// }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws
	Exception {
	return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// 	http.cors().and().csrf().disable()
	// 			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	// 			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	// 			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
	// 			.antMatchers("/api/test/**").permitAll()
	// 			.anyRequest().authenticated();

	// 	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	// }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable()
	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//no session occupied so makes us ensure each request is authenticated
	.authorizeRequests().antMatchers("/api/auth/**").permitAll()//WHITELIST-means no token needed
	.antMatchers("/api/test/**").permitAll()//WHITELIST-means no token needed
	.anyRequest().authenticated();//any other request must be authenticated

	http.authenticationProvider(authenticationProvider());

	http.addFilterBefore(authenticationJwtTokenFilter(),
	UsernamePasswordAuthenticationFilter.class);// arg1(filter 1) is applied before arg2(filter 2)
	//only if we get username and password from JWT we can create UsernamePasswordAuthentication instance

	return http.build();
	}
}
