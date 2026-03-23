package com.everyChallenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.everyChallenge.dto.request.ReplyInsertRequest;
import com.everyChallenge.dto.response.ReplyDto;
import com.everyChallenge.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyMapper replyMapper;
	
	public List<ReplyDto> getReplyList(Long challengeId){
		List<ReplyDto> reply = replyMapper.selectReply(challengeId);
		
		return reply;
	}
	
	public void insertReply(ReplyInsertRequest reply) {
		replyMapper.insertReply(reply);
	}
	
	public void deleteReply(Long id) {
		replyMapper.deleteReply(id);
	}
	
}
