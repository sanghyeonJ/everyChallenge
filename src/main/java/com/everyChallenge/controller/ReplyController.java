package com.everyChallenge.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everyChallenge.config.CustomUserDetails;
import com.everyChallenge.dto.request.ReplyInsertRequest;
import com.everyChallenge.service.ReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reply/{id}")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;
	
	@PostMapping("/insert")
	public String insertReply(@PathVariable("id") Long challengeId, @AuthenticationPrincipal CustomUserDetails user, ReplyInsertRequest reply, RedirectAttributes redirectAttributes) {
		if(user == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		Long userId = user.getId();
		reply.setChallengeId(challengeId);
		reply.setUserId(userId);
		replyService.insertReply(reply);
		return "redirect:/challenge/" + challengeId;
	}
	
	@PostMapping("/delete/{replyId}")
	public String deleteReply(@PathVariable("id") Long challengeId, @PathVariable("replyId") Long id) {
		replyService.deleteReply(id);
		return "redirect:/challenge/" + challengeId;
	}
	
}
