package com.everyChallenge.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.everyChallenge.domain.User;

import lombok.Data;

@Data
public class ChallengeDto {

	private Long id;
	private String title;
	private String content;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime realStartDate;
	private LocalDateTime realEndDate;
	private String visibility;
	private Boolean isDeleted;
	private String state;
	private LocalDateTime createAt;
	
	private User user;
	
}
