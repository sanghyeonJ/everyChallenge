package com.everyChallenge.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.everyChallenge.config.CustomUserDetails;
import com.everyChallenge.domain.User;
import com.everyChallenge.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserMapper userMapper;
	
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		User user = userMapper.findByLoginId(loginId);
		
		if(user == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다." + loginId);
		}
		
		return new CustomUserDetails(user);
	}
	
}
