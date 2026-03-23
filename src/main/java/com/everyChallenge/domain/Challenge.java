package com.everyChallenge.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Challenge {
	
	private Long id;
	private String userId;
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
