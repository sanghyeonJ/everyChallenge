package com.everyChallenge.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.everyChallenge.domain.Challenge;
import com.everyChallenge.dto.request.ChallengeInsertRequest;
import com.everyChallenge.dto.request.ChallengeUpdateRequest;
import com.everyChallenge.dto.response.ChallengeDto;
import com.everyChallenge.dto.response.ChallengeListDto;
import com.everyChallenge.mapper.ChallengeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {
	
	private final ChallengeMapper challengeMapper;
	
	private ChallengeListDto toListDto(Challenge c) {
	    ChallengeListDto dto = new ChallengeListDto();
	    dto.setId(c.getId());
	    dto.setUser_id(c.getUserId());
	    dto.setTitle(c.getTitle());
	    dto.setContent(c.getContent());
	    dto.setStart_date(c.getStartDate());
	    dto.setEnd_date(c.getEndDate());
	    dto.setReal_start_date(c.getRealStartDate());
	    dto.setReal_end_date(c.getRealEndDate());
	    dto.setState(c.getState());
	    if (c.getEndDate() != null && c.getEndDate().isBefore(java.time.LocalDate.now())
    	    && ("READY".equals(c.getState()) || "IN_PROGRESS".equals(c.getState()))) {
    	    dto.setState("FAIL");
    	}
	    dto.setCreate_at(c.getCreateAt());
	    dto.setUser(c.getUser());
	    return dto;
	}

	public List<ChallengeListDto> getChallengeList(){
		List<Challenge> challenges = challengeMapper.selectChallengeVisibility();
		List<ChallengeListDto> challengesListDto = challenges.stream().map(this::toListDto).toList();
		
		return challengesListDto;
	};
	
	public void challengeInsert(Long userId, ChallengeInsertRequest request) {
		request.setUserId(userId);
		challengeMapper.insertChallenge(request);
	}
	
	public ChallengeDto getChallengeDetail(Long id) {
		ChallengeDto dto = challengeMapper.findById(id);
		if (dto.getEndDate() != null && dto.getEndDate().isBefore(java.time.LocalDate.now())
    	    && ("READY".equals(dto.getState()) || "IN_PROGRESS".equals(dto.getState()))) {
    	    dto.setState("FAIL");
    	}
		return dto;
	}
	
	public void challengeDelete(Long id) {
		challengeMapper.delete(id);
	}
	
	public void challengeUpdate(ChallengeUpdateRequest request) {
		challengeMapper.update(request);
	}
	
	public void challengeStart(Long id, LocalDateTime date) {
		challengeMapper.challengeStart(id, date);
	}
	
	public void challengeSuccess(Long id, LocalDateTime date) {
		challengeMapper.challengeSuccess(id, date);
	}
	
	public void challengeGiveup(Long id, LocalDateTime date) {
		challengeMapper.challengeGiveup(id, date);
	}
	
}
