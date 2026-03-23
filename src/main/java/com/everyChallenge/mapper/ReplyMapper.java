package com.everyChallenge.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.everyChallenge.dto.request.ReplyInsertRequest;
import com.everyChallenge.dto.response.ReplyDto;

@Mapper
public interface ReplyMapper {

	List<ReplyDto> selectReply(@Param("challengeId") Long challengeId);
	
	void insertReply(ReplyInsertRequest reply);
	
	void deleteReply(@Param("id") Long id);
	
}
