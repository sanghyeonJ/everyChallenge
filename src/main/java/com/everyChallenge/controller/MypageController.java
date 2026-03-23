package com.everyChallenge.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everyChallenge.config.CustomUserDetails;
import com.everyChallenge.domain.Challenge;
import com.everyChallenge.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor

public class MypageController {
	
	private final MypageService mypageService;

	@GetMapping("")
	public String mypage(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		if(user == null) {
			return "redirect:/login";
		}
		Long id = user.getId();
		List<Challenge> list = mypageService.getUserChallenge(id);
		
		model.addAttribute("list", list);
		
		return "mypage";
	}
	
}
