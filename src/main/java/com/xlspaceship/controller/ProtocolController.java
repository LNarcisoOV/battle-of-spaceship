package com.xlspaceship.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protocol/game")
public class ProtocolController {
	
	@PostMapping("/new")
	public String newGame() {
		return "aaaa";
	}

}
