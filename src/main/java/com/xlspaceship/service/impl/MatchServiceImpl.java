package com.xlspaceship.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xlspaceship.model.Match;
import com.xlspaceship.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

	private List<Match> matchList = new ArrayList<Match>();

	public Match creatNewGame(Match matchRequest){
		Match match = createMatch(matchRequest);
		matchList.add(match);
		return match;
	}

	private Match createMatch(Match matchRequest) {
		Match match = new Match();

		match.setUserId("player" + (matchList.size() + 1));
		match.setFullName(matchRequest.getFullName());
		match.setGameId("match-" + (matchList.size() + 1));
		match.setStarting(matchRequest.getUserId());

		return match;
	}
}
