package com.everyChallenge.dto.response;

import java.time.LocalDateTime;

import com.everyChallenge.domain.User;

import lombok.Data;

@Data
public class ReplyDto {

	private Long id;
	private Long challengeId;
	private Long userId;
	private String content;
	private LocalDateTime createAt;
    private Boolean isDeleted;
    
    private User user;
    
}
