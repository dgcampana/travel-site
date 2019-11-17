package com.kmp.site.travelweb.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kmp.site.travelweb.application.dto.login.RequestUserCheck;
import com.kmp.site.travelweb.application.dto.login.ResponseUserCheck;
import com.kmp.site.travelweb.domain.client.LoginClient;

@Service
public class LoginService implements UserDetailsService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${properties.secure.magicCode}")
	private String secureCode;
	
	@Autowired
	private LoginClient loginClient;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		RequestUserCheck req = new RequestUserCheck(username, passwordEncoder.encode(this.secureCode));
		ResponseUserCheck user = loginClient.userCheck(req);
		
		if(user.getCode() == 404) {
			logger.error("Error en el Login: no existe el usuario '{}' en el sistema!", username);
        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        
        if(authorities.isEmpty()) {
        	logger.error("Error en el Login: Usuario '{}' no tiene roles asignados!", username);
        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
		
        return new User(user.getName(), user.getPassword(), true, true, true, true, authorities);
	}

}
