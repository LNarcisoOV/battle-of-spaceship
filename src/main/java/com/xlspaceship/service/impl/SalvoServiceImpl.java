package com.xlspaceship.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlspaceship.model.Match;
import com.xlspaceship.model.MatchDTO;
import com.xlspaceship.model.Player;
import com.xlspaceship.service.MatchService;
import com.xlspaceship.service.SalvoService;

@Service
public class SalvoServiceImpl implements SalvoService {
	
	@Autowired
	private MatchService matchService;

	@Override
	public MatchDTO applySalvoOfShots(Match matchRequest) {
		String[] salvo = replaceLettersToNumbers(matchRequest);
		Match match = matchService.getFirstMatchBy(matchRequest.getGameId());

		if (match.getSelf().getUserId().equals(match.getGame().getPlayerTurn())) {
			applyShots(match, matchRequest, match.getOpponent(), salvo);
			match.getGame().setPlayerTurn(match.getOpponent().getUserId());
		} else {
			applyShots(match, matchRequest, match.getSelf(), salvo);
			match.getGame().setPlayerTurn(match.getSelf().getUserId());
		}

		return null;
	}

	private void applyShots(Match match, Match matchRequest, Player player, String[] salvo) {
		String[][] board = player.getBoardForSalvo();
		for (int salvoIndex = 0; salvoIndex < salvo.length; salvoIndex++) {

			String[] splitedSalvo = salvo[salvoIndex].split("x");
			int shotRow = Integer.parseInt(splitedSalvo[0]);
			int shotColumn = Integer.parseInt(splitedSalvo[1]);

			if (board[shotRow][shotColumn].equals("*") || board[shotRow][shotColumn].equals("X")) {
				board[shotRow][shotColumn] = "X";
			} else {
				board[shotRow][shotColumn] = "-";
			}
		}
		player.setBoardForSalvo(board);
		matchService.fillBoardWithDotsAndCreateArrayForPlayer(player, board);
	}

	private String[] replaceLettersToNumbers(Match matchRequest) {
		String[] salvo = new String[5];
		salvo = matchRequest.getSalvo();
		if (matchRequest.getSalvo() != null || salvo.length > 0) {
			for (int i = 0; i < salvo.length; i++) {
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
