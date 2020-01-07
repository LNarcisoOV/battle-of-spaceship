package com.xlspaceship.service;

import java.util.List;

import com.xlspaceship.model.Match;

public interface MatchService {
	public Match creatNewGame(Match matchRequest);
	public List<Match> getMatchList();
}
