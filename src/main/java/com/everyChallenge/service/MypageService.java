package com.everyChallenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.everyChallenge.domain.Challenge;
import com.everyChallenge.mapper.MypageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageService {
	
	private final MypageMapper mypageMapper;

	public List<Challenge> getUserChallenge(Long id){
		return mypageMapper.selectUserChallenge(id);
	}
	
}
