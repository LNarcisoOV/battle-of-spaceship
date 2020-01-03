package com.xlspaceship.controller;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlspaceship.model.Player;

@RestController
@RequestMapping("/user/game")
public class UserController {
	
	@GetMapping("/{id}")
	public ResponseEntity<Player> newGame(@PathVariable String id) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<Player> test() {
		Player player = new Player();
		Arrays.fill(player.getBoard(), "................");
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

}
