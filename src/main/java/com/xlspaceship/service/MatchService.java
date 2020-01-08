package com.xlspaceship.service;

import java.util.List;

import com.xlspaceship.model.Match;
import com.xlspaceship.model.MatchDTO;
import com.xlspaceship.model.Player;

public interface MatchService {
	public MatchDTO creatNewGame(Match matchRequest);
	public List<Match> getMatchesList();
	public MatchDTO getMatchByIdForXLSS2(String gameId);
	public Match getFirstMatchBy(String gameId);
	public void fillBoardWithDotsAndCreateArrayForPlayer(Player player, String[][] board);
}
