package com.everyChallenge.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.everyChallenge.domain.User;
import com.everyChallenge.dto.request.JoinRequest;
import com.everyChallenge.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	public User findByLoginId(String LoginId) {
		return userMapper.findByLoginId(LoginId);
	}
	
	public void insertUser(JoinRequest user) {
		if(userMapper.findByLoginId(user.getLoginId()) != null) {
			throw new IllegalArgumentException("이미 사용중인 계정입니다.");
		}
		if(!user.getPassword().equals(user.getPasswordConfirm())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userMapper.insertUser(user);
	}
	
}
