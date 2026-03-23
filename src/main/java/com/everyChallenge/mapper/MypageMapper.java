package com.everyChallenge.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.everyChallenge.domain.Challenge;

@Mapper
public interface MypageMapper {

	public List<Challenge> selectUserChallenge(@Param("userId") Long id);
	
}
