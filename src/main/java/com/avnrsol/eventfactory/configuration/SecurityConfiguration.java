package com.avnrsol.eventfactory.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
//		http
//        .authorizeRequests()
//        	.antMatchers("/").permitAll()
//            .antMatchers("/customer/**").access("hasRole('ROLE_USER') or hasRole('ROLE_UNAUTH')")
//            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//            .antMatchers("/checkout").access("hasRole('ROLE_USER')")
//            .and()
//        .formLogin()
//            .loginPage("/login")
//            .failureUrl("/login?error")
//            .usernameParameter("email")
//            .passwordParameter("password")
//            .successHandler(customAuthenticationSuccessHandler)
//            .permitAll()
//            .and()
//        .logout()
//        	.logoutSuccessUrl("/login?logout")
//            .permitAll()
//    		.and()
//    	.csrf();
		
		http.
			authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
				.anyRequest().authenticated()
				.and().csrf().disable()
				
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/home")
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/service/**", "/serviceCategory/**", "/about/**", "/faq/**", "/cart/**", 
	    		   "/resources/**", "/static/**", "/css/**", "/css/colors/**", "/js/**", "/images/**", "/adminDash/**",
	    		   "/fonts/**");
	}
}