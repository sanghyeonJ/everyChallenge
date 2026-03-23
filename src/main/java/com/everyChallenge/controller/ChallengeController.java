package com.everyChallenge.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everyChallenge.config.CustomUserDetails;
import com.everyChallenge.dto.request.ChallengeInsertRequest;
import com.everyChallenge.dto.request.ChallengeUpdateRequest;
import com.everyChallenge.dto.request.ReplyInsertRequest;
import com.everyChallenge.dto.response.ChallengeDto;
import com.everyChallenge.dto.response.ReplyDto;
import com.everyChallenge.service.ChallengeService;
import com.everyChallenge.service.ReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
	
	private final ChallengeService challengeService;
	private final ReplyService replyService;

	@GetMapping("/insert")
	public String challengeListPage(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		ChallengeInsertRequest challenge = new ChallengeInsertRequest();
		challenge.setVisibility("PUBLIC");
		model.addAttribute("challenge", challenge);
		return "challenge/insert";
	}
	
	@PostMapping("/insert")
	public String challengeInsert(@AuthenticationPrincipal CustomUserDetails user, ChallengeInsertRequest challenge, RedirectAttributes redirectAttributes) {
		if(user == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		challengeService.challengeInsert(user.getId(), challenge);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String challengeViewPage(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails user, Model model) {
		ChallengeDto challenge = challengeService.getChallengeDetail(id);
		Boolean isOwner = user != null && user.getId().equals(challenge.getUser().getId());
		Boolean canModify = user != null && (isOwner || user.isAdmin());
		String state = challenge.getState();
		
		List<ReplyDto> replyList = replyService.getReplyList(id);
		
		model.addAttribute("challenge", challenge);
		model.addAttribute("canModify", canModify);
		model.addAttribute("isOwner", isOwner);
		model.addAttribute("state", state);
		model.addAttribute("replyList", replyList);
		model.addAttribute("reply", new ReplyInsertRequest());
		model.addAttribute("currentUserId", user != null ? user.getId() : null);
		model.addAttribute("isAdmin", user != null ? user.isAdmin() : false);
		
		return "challenge/detail";
	}
	
	@PostMapping("/{id}/delete")
	public String challengeDelete(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails user, RedirectAttributes redirectAttributes) {
		if(user == null) {
			redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		try {
			challengeService.challengeDelete(id);
			redirectAttributes.addFlashAttribute("message", "도전이 삭제되었습니다.");
		}catch(IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/";
	}
	
	@GetMapping("/{id}/update")
	public String challengeEditPage(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails user, RedirectAttributes redirectAttributes, Model model) {
		if(user == null) {
			redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		ChallengeDto challenge = challengeService.getChallengeDetail(id);
		model.addAttribute("challenge", challenge);
		return "challenge/edit";
	}
	
	@PostMapping("/{id}/update")
	public String challengeUpdate(@PathVariable("id") Long id, ChallengeUpdateRequest request) {
		request.setId(id);
		challengeService.challengeUpdate(request);
		return "redirect:/challenge/" + id;
	}
	
	
}
