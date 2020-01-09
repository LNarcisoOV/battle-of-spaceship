package com.xlspaceship.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlspaceship.model.Game;
import com.xlspaceship.model.Match;
import com.xlspaceship.model.MatchDTO;
import com.xlspaceship.model.Player;
import com.xlspaceship.service.MatchService;
import com.xlspaceship.service.SalvoService;

@Service
public class SalvoServiceImpl implements SalvoService {
	
	@Autowired
	private MatchService matchService;
	
	private Map<String, String> salvoMap = new HashMap<>();

	@Override
	public MatchDTO applySalvoOfShots(Match matchRequest) {
		salvoMap = new HashMap<>();
		Game turn = new Game();
		Match match = matchService.getMatchBy(matchRequest.getGameId());

		if (match.getSelf().getUserId().equals(match.getGame().getPlayerTurn())) {
			applyShots(match, matchRequest, match.getOpponent());
			turn.setPlayerTurn(match.getGame().getPlayerTurn());
			match.getGame().setPlayerTurn(match.getOpponent().getUserId());
		} else {
			applyShots(match, matchRequest, match.getSelf());
			turn.setPlayerTurn(match.getGame().getPlayerTurn());
			match.getGame().setPlayerTurn(match.getSelf().getUserId());
		}
		
		
		MatchDTO matchDTO = createMatchDTOToReturnForXLSS4(salvoMap, turn);
		return matchDTO;
	}

	private MatchDTO createMatchDTOToReturnForXLSS4(Map<String, String> salvoMap2, Game turn) {
		MatchDTO matchForJSon = new MatchDTO();
		matchForJSon.setGame(turn);
		matchForJSon.setSalvo(salvoMap);
		return matchForJSon;
	}
	
	private void applyShots(Match match, Match matchRequest, Player player) {
		String[] originalSalvo = matchRequest.getSalvo();
		String[] replacedSalvo = replaceLettersToNumbers(matchRequest);
		String[][] board = player.getBoardForSalvo();
		
		for (int salvoIndex = 0; salvoIndex < replacedSalvo.length; salvoIndex++) {
			String[] splitedSalvo = replacedSalvo[salvoIndex].split("x");
			int shotRow = Integer.parseInt(splitedSalvo[0]);
			int shotColumn = Integer.parseInt(splitedSalvo[1]);

			if (board[shotRow][shotColumn].equals("*") || board[shotRow][shotColumn].equals("X")) {
				board[shotRow][shotColumn] = "X";
				salvoMap.put(originalSalvo[salvoIndex], "hit");
			} else {
				board[shotRow][shotColumn] = "-";
				salvoMap.put(originalSalvo[salvoIndex], "miss");
			}
		}
		player.setBoardForSalvo(board);
		matchService.fillBoardWithDotsAndCreateArrayForPlayer(player, board);
	}

	private String[] replaceLettersToNumbers(Match matchRequest) {
		String[] salvo = new String[5];
		
		if (matchRequest.getSalvo() != null || matchRequest.getSalvo().length > 0) {
			for (int i = 0; i < matchRequest.getSalvo().length; i++) {
				salvo[i] = matchRequest.getSalvo()[i];
				if (salvo[i].contains("A")) {
					salvo[i] = salvo[i].replace("A", "10");
				}
				if (salvo[i].contains("B")) {
					salvo[i] = salvo[i].replace("B", "11");
				}
				if (salvo[i].contains("C")) {
					salvo[i] = salvo[i].replace("C", "12");
				}
				if (salvo[i].contains("D")) {
					salvo[i] = salvo[i].replace("D", "13");
				}
				if (salvo[i].contains("E")) {
					salvo[i] = salvo[i].replace("E", "14");
				}
				if (salvo[i].contains("F")) {
					salvo[i] = salvo[i].replace("F", "15");
				}
			}
		}
		return salvo;
	}
}
