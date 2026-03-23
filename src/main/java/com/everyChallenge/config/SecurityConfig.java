package com.everyChallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 인증, 권한설정을 하는 설계도라고 부트에게 알려줌
@EnableWebSecurity
public class SecurityConfig {

	/*
	 * 비밀번호 암호화 방식 설정
	 * Bcrypt : 단방향 해시 암호화(복호화 불가)
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf(csrf->csrf.disable())  // csrf 끄기
			.authorizeHttpRequests(auth -> auth
					// 로그인없이 누구나 접속가능
					.requestMatchers(
							"/",
							"/login",
							"/register",
							"/css/**",
							"/js/**",
							"/images/**"
							).permitAll()
					
					// admin 권한 필요
					//.requestMatchers("/student/api/file/**").hasRole("ADMIN")
					
					// USER 이상 권한 필요 (조회만 가능)
					.requestMatchers(
							"/challenge/insert",
							"/mypage").authenticated()
					
					
					
					// 나머지는 인증 필요
					.anyRequest().permitAll()  // 모든 url 허용
					//.anyRequest().authenticated()
			)

			// 로그인 설정
			.formLogin(form -> form
					.loginPage("/login")  // 로그인 페이지 url
					.loginProcessingUrl("/login")  // 로그인 처리 url(post)
					.defaultSuccessUrl("/", true)  // 로그인 성공시 이동
					.failureUrl("/login?error=true")  // 로그인 실패시 이동
					.usernameParameter("username")  // 로그인 폼의 username 필드명
					.passwordParameter("password")  // 로그인 폼의 password 필드명
					.permitAll()
					)
			
			// 로그아웃
			.logout(logout -> logout
					.logoutUrl("/logout")  // 로그아웃 요청주소
					.logoutSuccessUrl("/")  // 로그아웃 후 이동 페이지
					.invalidateHttpSession(true)  // 모든 세션 제거
					.deleteCookies("JSESSIONID")  // 쿠키 삭제
					.permitAll()
					);
		
		return http.build();
	}
	
}

