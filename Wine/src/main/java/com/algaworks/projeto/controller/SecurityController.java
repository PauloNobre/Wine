package com.algaworks.projeto.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		
		if(user != null) {
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			for(GrantedAuthority authorite : authorities) {
				if(authorite.getAuthority().equals("ROLE_LISTAR_VINHO")) {
					return "redirect:/vinhos";
				}
			}
			return "redirect:/vinhos/novo";
		}
		return "login";
	}
}
