package com.everyChallenge.dto.request;

import lombok.Data;

@Data
public class ReplyInsertRequest {

	private Long challengeId;
	private Long userId;
	private String content;
	
}
