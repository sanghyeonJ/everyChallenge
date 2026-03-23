package com.everyChallenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everyChallenge.dto.request.JoinRequest;
import com.everyChallenge.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


	@GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        
        if (error != null) {
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
        }
        
        if (logout != null) {
            model.addAttribute("logoutMsg", "로그아웃되었습니다.");
        }
        
        return "login";
    }
	
	@GetMapping("/join")
	public String joinPage(Model model) {
		model.addAttribute("user", new JoinRequest());
		return "join";
	}
	
	@PostMapping("/join")
	public String join(JoinRequest user, RedirectAttributes redirectAttributes) {
		try {
			userService.insertUser(user);
			return "redirect:/";
		}catch(IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
			return "redirect:/join";
		}
	}
	
}
