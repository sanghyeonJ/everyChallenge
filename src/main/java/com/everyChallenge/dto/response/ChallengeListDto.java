package com.everyChallenge.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.everyChallenge.domain.User;

import lombok.Data;

@Data
public class ChallengeListDto {

	private Long id;
	private String user_id;
	private String title;
	private String content;
	private LocalDate start_date;
	private LocalDate end_date;
	private LocalDateTime real_start_date;
	private LocalDateTime real_end_date;
	private String state;
	private LocalDateTime create_at;
	
	private User user;
	
}
