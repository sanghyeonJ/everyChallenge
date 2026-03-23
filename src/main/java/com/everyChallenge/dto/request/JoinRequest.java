package com.everyChallenge.dto.request;

import lombok.Data;

@Data
public class JoinRequest {
	
	private String loginId;
	private String password;
	private String passwordConfirm;
	private String userName;

}
