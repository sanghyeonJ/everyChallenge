package com.everyChallenge.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.everyChallenge.domain.Challenge;
import com.everyChallenge.dto.request.ChallengeInsertRequest;
import com.everyChallenge.dto.request.ChallengeUpdateRequest;
import com.everyChallenge.dto.response.ChallengeDto;

@Mapper
public interface ChallengeMapper {

	// 전체 가져오기
	List<Challenge> selectChallengeVisibility();
	
	// 생성
	void insertChallenge(ChallengeInsertRequest challenge);
	
	ChallengeDto findById(Long id);
	
	// 수정
	void update(ChallengeUpdateRequest challenge);
	
	// 삭제
	void delete(Long id);
	
	// 검색
	List<Challenge> searchTitle(String keyword);
	
	// 시작
	void challengeStart(@Param("id") Long id, @Param("date") LocalDateTime date);
	
	// 성공
	void challengeSuccess(@Param("id") Long id, @Param("date") LocalDateTime date);
	
	// 포기
	void challengeGiveup(@Param("id") Long id, @Param("date") LocalDateTime date);
	
}
