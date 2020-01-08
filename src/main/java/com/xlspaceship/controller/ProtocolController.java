package com.xlspaceship.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlspaceship.model.Match;
import com.xlspaceship.model.MatchDTO;
import com.xlspaceship.service.MatchService;
import com.xlspaceship.service.SalvoService;

@RestController
@RequestMapping("/protocol/game")
public class ProtocolController {
	
	@Autowired 
	private MatchService matchService;
	
	@Autowired
	private SalvoService salvoService;
	
	@PostMapping("/new")
	public ResponseEntity<MatchDTO> newGame(@RequestBody Match matchRequest) {
		MatchDTO matchDTO = matchService.creatNewGame(matchRequest);
		return new ResponseEntity<>(matchDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String[]> receiveASalvoOfShots(@PathVariable String id, @RequestBody Match matchRequest) {
		if(Strings.isNotEmpty(id) && matchRequest.getSalvo() != null && matchRequest.getSalvo().length > 0) {
			matchRequest.setGameId(id);
			MatchDTO matchDTO = salvoService.applySalvoOfShots(matchRequest);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}

}
