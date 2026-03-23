package com.everyChallenge.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.everyChallenge.domain.User;
import com.everyChallenge.dto.request.JoinRequest;

@Mapper
public interface UserMapper {

	User findByLoginId(String loginId);
	
	void insertUser(JoinRequest user);
	
	int existsByLoginId(String loginId);
	
}
