package com.everyChallenge.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Reply {
	
	private Long id;
	private Long challengeId;
	private Long userId;
	private String content;
	private LocalDateTime createAt;
    private Boolean isDeleted;

}
