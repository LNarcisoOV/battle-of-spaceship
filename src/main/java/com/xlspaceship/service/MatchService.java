package com.xlspaceship.service;

import java.util.List;

import com.xlspaceship.model.Match;
import com.xlspaceship.model.MatchDTO;

public interface MatchService {
	public MatchDTO creatNewGame(Match matchRequest);
	public List<Match> getMatchesList();
	public MatchDTO getMatchByIdForXLSS2(String gameId);
}
