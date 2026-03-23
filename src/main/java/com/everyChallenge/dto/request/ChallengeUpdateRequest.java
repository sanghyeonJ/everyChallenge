package com.everyChallenge.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChallengeUpdateRequest {

	private Long id;
	private Long userId;
	private String title;
	private String content;
	private LocalDate startDate;
	private LocalDate endDate;
	private String visibility;
	
}
