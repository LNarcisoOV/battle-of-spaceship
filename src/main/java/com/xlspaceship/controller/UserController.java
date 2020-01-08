package com.xlspaceship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlspaceship.model.MatchDTO;
import com.xlspaceship.service.MatchService;

@RestController
@RequestMapping("/user/game")
public class UserController {
	
	@Autowired
	private MatchService matchService;
	
	@GetMapping("/{id}")
	public ResponseEntity<MatchDTO> getMatchBy(@PathVariable String id) {
		MatchDTO matchDTO = matchService.getMatchByIdForXLSS2(id);

		if (matchDTO != null) {
			return new ResponseEntity<>(matchDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
