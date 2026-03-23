package com.everyChallenge.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everyChallenge.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
public class ChallengeApiController {

	private final ChallengeService challengeService;
	
	@PostMapping("/{id}/start")
	public ResponseEntity<Map<String, Object>> start(@PathVariable("id") Long id){
		challengeService.challengeStart(id, LocalDateTime.now());
		
		return ResponseEntity.ok(Map.of(
					"success", true,
					"state", "IN_PROGRESS"
				));
	};
	
	@PostMapping("/{id}/success")
	public ResponseEntity<Map<String, Object>> success(@PathVariable("id") Long id){
		challengeService.challengeSuccess(id, LocalDateTime.now());
		
		return ResponseEntity.ok(Map.of(
					"success", true,
					"state", "SUCCESS"
				));
	}
	
	@PostMapping("/{id}/giveup")
	public ResponseEntity<Map<String, Object>> giveup(@PathVariable("id") Long id){
		challengeService.challengeGiveup(id, LocalDateTime.now());
		
		return ResponseEntity.ok(Map.of(
					"success", true,
					"state", "GIVE_UP"
				));
	}
	
}
