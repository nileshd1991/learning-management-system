package com.lm.app.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lm.app.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.lm.app.model.User user = userRepo.findByUserName(username);
		Collection<? extends GrantedAuthority> authorities
        = Arrays.asList(user.getRole())
        .stream()
        .map(authority -> new SimpleGrantedAuthority(authority))
        .collect(Collectors.toList());

//		User principal = new User(claims.getSubject(), "",
//        authorities);
//		
		
		
//		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(user.getRole());
//		List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
//		list.add(authority);
		return new User(user.getUserName(), user.getPassword(), authorities);
	}
	
	

}
