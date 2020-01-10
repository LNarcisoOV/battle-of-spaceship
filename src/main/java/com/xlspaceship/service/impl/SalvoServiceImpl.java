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
		Match match = matchService.getMatchBy(matchRequest.getGameId());
		Game game = new Game();
		
		if(match.getSelf().getListOfPositionsInTheBoard() != null 
		&& !match.getSelf().getListOfPositionsInTheBoard().isEmpty()
		&& match.getOpponent().getListOfPositionsInTheBoard() != null 
		&& !match.getOpponent().getListOfPositionsInTheBoard().isEmpty()) {
			salvoMap = new HashMap<>();
			
			if (match.getSelf().getUserId().equals(match.getGame().getPlayerTurn())) {
				applyShots(matchRequest, match.getOpponent());
				if(match.getOpponent().getListOfPositionsInTheBoard() == null || match.getOpponent().getListOfPositionsInTheBoard().isEmpty()) {
					game.setWon(match.getSelf().getUserId());
					match.getGame().setPlayerTurn(null);
					match.getGame().setWon(match.getSelf().getUserId());
				}else {
					game.setPlayerTurn(match.getSelf().getUserId());//);match.getGame().getPlayerTurn());
					match.getGame().setPlayerTurn(match.getOpponent().getUserId());
				}
			} else {
				applyShots(matchRequest, match.getSelf());
				if(match.getSelf().getListOfPositionsInTheBoard() == null || match.getSelf().getListOfPositionsInTheBoard().isEmpty()) {
					game.setWon(match.getOpponent().getUserId());
					match.getGame().setPlayerTurn(null);
					match.getGame().setWon(match.getOpponent().getUserId());
				}else {
					game.setPlayerTurn(match.getOpponent().getUserId());//match.getGame().getPlayerTurn());
					match.getGame().setPlayerTurn(match.getSelf().getUserId());
				}
			}
			
			MatchDTO matchDTO = createMatchDTOToReturnForXLSS4(game);
			return matchDTO;
		} else {
			MatchDTO matchDTO = createMatchDTOToReturnForXLSS4ForEndedGame(match, matchRequest);
			return matchDTO;
		}
	}

	private MatchDTO createMatchDTOToReturnForXLSS4(Game turn) {
		MatchDTO matchForJSon = new MatchDTO();
		matchForJSon.setGame(turn);
		matchForJSon.setSalvo(salvoMap);
		matchForJSon.setHttpNotFoundCode(null);
		return matchForJSon;
	}
	
	private MatchDTO createMatchDTOToReturnForXLSS4ForEndedGame(Match match, Match matchRequest) {
		for (int i = 0; i < matchRequest.getSalvo().length; i++) {
			salvoMap.put(matchRequest.getSalvo()[i], "miss");
		}
		
		match.getGame().setPlayerTurn(null);
		MatchDTO matchForJSon = new MatchDTO();
		matchForJSon.setGame(match.getGame());
		matchForJSon.setSalvo(salvoMap);
		matchForJSon.setHttpNotFoundCode(404);
		return matchForJSon;
	}
	
	private void applyShots(Match matchRequest, Player opponent) {
		String[] originalSalvo = matchRequest.getSalvo();
		String[] replacedSalvo = replaceLettersToNumbers(matchRequest);
		String[][] board = opponent.getBoardForSalvo();
		
		for (int salvoIndex = 0; salvoIndex < replacedSalvo.length; salvoIndex++) {
			String[] splitedSalvo = replacedSalvo[salvoIndex].split("x");
			int shotRow = Integer.parseInt(splitedSalvo[0]);
			int shotColumn = Integer.parseInt(splitedSalvo[1]);

			if (board[shotRow][shotColumn].equals("X")) {
				board[shotRow][shotColumn] = "X";
				salvoMap.put(originalSalvo[salvoIndex], "miss");
			} else if (board[shotRow][shotColumn].equals("*")) {
				board[shotRow][shotColumn] = "X";
				salvoMap.put(originalSalvo[salvoIndex], "hit");
			} else if (board[shotRow][shotColumn].equals(".") || board[shotRow][shotColumn].equals("-")) {
				board[shotRow][shotColumn] = "-";
				salvoMap.put(originalSalvo[salvoIndex], "miss");

			}
			
			if(isSpaceshipKilled(board, opponent)) {
				salvoMap.replace(originalSalvo[salvoIndex], "hit", "kill");
			}
		}
		
		opponent.setBoardForSalvo(board);
		matchService.fillBoardWithDotsAndCreateArrayForPlayer(opponent, board);
	}

	private boolean isSpaceshipKilled(String[][] board, Player opponent) {
		boolean isSpaceshipKilled = true;
		for (int i = 0; i < opponent.getListOfPositionsInTheBoard().size(); i++) {
			isSpaceshipKilled = true;
			int[] positions = opponent.getListOfPositionsInTheBoard().get(i);

			rowLoop:
			for (int row = positions[0]; row <= positions[2]; row++) {
				for (int column = positions[1]; column <= positions[3]; column++) {
					if (board[row][column].equals("*")) {
						isSpaceshipKilled = false;
						break rowLoop;
					}
				}
			}

			if (isSpaceshipKilled) {
				opponent.getListOfPositionsInTheBoard().remove(i);
				break;
			}
		}
			
		return isSpaceshipKilled;
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
