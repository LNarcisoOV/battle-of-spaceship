package com.xlspaceship.service;

import org.springframework.stereotype.Service;

import com.xlspaceship.model.Match;

@Service
public interface MatchService {
	public Match creatNewGame(Match matchRequest);

}
