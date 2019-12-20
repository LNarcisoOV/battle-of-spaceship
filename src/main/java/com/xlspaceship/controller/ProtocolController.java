package com.xlspaceship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlspaceship.model.Match;
import com.xlspaceship.service.MatchService;

@RestController
@RequestMapping("/protocol/game")
public class ProtocolController {
	
	@Autowired 
	private MatchService matchService;
	
	@PostMapping("/new")
	public ResponseEntity<Match> newGame(@RequestBody Match matchRequest) {
		Match match = matchService.creatNewGame(matchRequest);
		return new ResponseEntity<>(match, HttpStatus.CREATED);
	}

}