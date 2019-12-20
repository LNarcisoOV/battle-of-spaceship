package com.xlspaceship.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlspaceship.model.Game;

@RestController
@RequestMapping("/protocol/game")
public class ProtocolController {
	
	@PostMapping("/new")
	public ResponseEntity<Game> newGame(@RequestBody Game game) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
