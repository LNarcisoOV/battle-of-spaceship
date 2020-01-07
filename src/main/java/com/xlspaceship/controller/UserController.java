package com.xlspaceship.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlspaceship.model.Match;
import com.xlspaceship.model.Player;
import com.xlspaceship.service.MatchService;

@RestController
@RequestMapping("/user/game")
public class UserController {
	
	@Autowired
	private MatchService matchService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Match> getMatchBy(@PathVariable String id) {
		Match match = matchService.getMatchBy(id);
		return new ResponseEntity<>(match, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<Player> test() {
		Player player = new Player();
		Arrays.fill(player.getBoard(), "................");
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

}
