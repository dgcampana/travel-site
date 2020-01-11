package com.kmp.site.travelweb.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kmp.site.travelweb.application.service.impl.LoginUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private LoginUserDetailsService loginService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(loginService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
    		.and().authorizeRequests()
				.antMatchers("/application/**","/js/**","/css/**","/img/**","/fonts/**","/vendor/**","/webjars/**").permitAll()
				.antMatchers("/", "/register", "/session/confirmation-email").permitAll()
				.antMatchers("/tours","/adventure","/gastronomy", "/detail", "/search/**").permitAll()
            	.anyRequest().authenticated()
            .and()
            	.formLogin().loginPage("/login").permitAll()
            .and()
            	.logout()
		            .deleteCookies("JSESSIONID")
		            .invalidateHttpSession(true)
		            .clearAuthentication(true)
		            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		            .logoutSuccessUrl("/login?logout")
		            .permitAll()
            .and()
            	.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
