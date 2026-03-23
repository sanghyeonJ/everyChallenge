package com.everyChallenge.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {

	private Long id;
    private String loginId;
	private String password;
	private String userName;
	private String role;
	private LocalDate createAt;
	private Boolean isDeleted;
	
}
