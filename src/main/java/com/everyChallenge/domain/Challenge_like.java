package com.everyChallenge.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Challenge_like {

	private Long id;
	private Long challengeId;
	private Long userId;
	private LocalDateTime createAt; 
	
}
