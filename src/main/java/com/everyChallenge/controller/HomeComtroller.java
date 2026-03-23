package com.everyChallenge.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.everyChallenge.dto.response.ChallengeListDto;
import com.everyChallenge.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeComtroller {
	
	private final ChallengeService challengeService;

	@GetMapping("/")
	public String home(Model model) {
		List<ChallengeListDto> challenges = challengeService.getChallengeList(); 
		
		model.addAttribute("challenges", challenges);
		
		return "home";
	}
	
}
