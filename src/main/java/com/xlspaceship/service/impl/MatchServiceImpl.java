package com.xlspaceship.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlspaceship.model.Game;
import com.xlspaceship.model.Match;
import com.xlspaceship.model.MatchDTO;
import com.xlspaceship.model.Player;
import com.xlspaceship.service.MatchService;
import com.xlspaceship.service.SpaceshipService;

@Service
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private SpaceshipService spaceshipService;

	private List<Match> matchList = new ArrayList<Match>();
	private int counterForMatchId = 0;
	private Random random = new Random();

	public MatchDTO creatNewGame(Match matchRequest){
		Match match = createMatch(matchRequest);
		return createMatchDTOToReturnForXLSS1(match);
	}

	public MatchDTO getMatchByIdForXLSS2(String gameId) {
		Match match = getFirstMatchBy(gameId);
		if(match != null) {
			return createMatchDTOToReturnForXLSS2(match);
		} else { 
			return null;
		}
	}

	private Match createMatch(Match matchRequest) {
		Match match = new Match();
		
		if(matchList == null || matchList.isEmpty()) {
			match = fillNewMatch(matchRequest, match);
		} else {
			List<Match> existedMatches = matchList.stream()
				.filter(m -> m.getSpaceshipProtocol() != null && m.getSpaceshipProtocol().getHostName().equals(matchRequest.getSpaceshipProtocol().getHostName()))
				.filter(m -> m.getSpaceshipProtocol() != null && m.getSpaceshipProtocol().getPort().equals(matchRequest.getSpaceshipProtocol().getPort()))
				.collect(Collectors.toList());
			
			if(existedMatches != null && !existedMatches.isEmpty() && existedMatches.get(existedMatches.size()-1).getOpponent() == null) {
				match = fillExistingMatchWithTheOpponent(matchRequest, existedMatches.get(existedMatches.size()-1));
			} else {
				match = fillNewMatch(matchRequest, match);
			}
		}
		
		return match;
	}

	private Match fillNewMatch(Match matchRequest, Match match) {
		match.setUserId(matchRequest.getUserId());
		match.setFullName(matchRequest.getFullName());
		match.setGameId("match-" + ++counterForMatchId);
		match.setStarting(matchRequest.getUserId());
		match.setSpaceshipProtocol(matchRequest.getSpaceshipProtocol());
		
		Player self = createPlayerAndBoard(match);
		match.setSelf(self);

		Game game = createPlayerTurnForStartTheMatch(match);
		match.setGame(game);
		
		matchList.add(match);
		return match;
	}

	private Match fillExistingMatchWithTheOpponent(Match matchRequest, Match existedMatch) {
		matchRequest.setGameId(existedMatch.getGameId());
		Player opponent = createPlayerAndBoard(matchRequest);
		existedMatch.setOpponent(opponent);
		return matchRequest;
	}

	private Player createPlayerAndBoard(Match match) {
		Player player = new Player();
		player.setUserId(match.getUserId());
		createSpaceshipsAndBoard(match, player);
		return player;
	}

	private void createSpaceshipsAndBoard(Match match, Player player) {
		String[][] board = new String[16][16];
		match.setListOfPositionsInTheBoard(new ArrayList<>());
		
		String[][] winger = spaceshipService.createWingerRandomly(random.nextInt(2));
		String[][] angle = spaceshipService.createAngleRandomly(random.nextInt(6));
		String[][] aClass = spaceshipService.createAClassRandomly(random.nextInt(4));
		String[][] bClass = spaceshipService.createBClassRandomly(random.nextInt(4));
		String[][] sClass = spaceshipService.createSClassRandomly(random.nextInt(4));
		
		findAvailablePlaceAndPutSpaceAtTheBoard(match, winger, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, angle, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, aClass, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, bClass, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, sClass, board);
		fillBoardWithDotsAndCreateArrayForPlayer(player, board);
	}

	private void findAvailablePlaceAndPutSpaceAtTheBoard(Match match, String[][] spaceship, String[][] board) {
		int[] rowAndColumnAvailable = findAnAvailablePlaceInTheBoard(board, spaceship);
		putSpaceshipAtTheBoard(match, board, spaceship, rowAndColumnAvailable);
	}

	private int[] findAnAvailablePlaceInTheBoard(String[][] board, String[][] spaceship) {
		boolean shallSearchAPlaceInTheBoard = true;
		int[] rowAndColumn = new int[2];
		
		while (shallSearchAPlaceInTheBoard) {
			boolean isArrayIndexOutOfBound = false;
			boolean shallCreateArraysWithIndex = true;
			int rowRandomly = random.nextInt(16);
			int columnRandomly = random.nextInt(16);
			
			try {
				if(rowRandomly < columnRandomly) {
					// Condition to find available places from the beginning to the end.
					outer:
					for (int row = rowRandomly; row < rowRandomly + spaceship.length; row++) {
						for (int column = columnRandomly; column < columnRandomly + spaceship[0].length; column++) {
							if (board[row][column] != null) {
								shallSearchAPlaceInTheBoard = true;
								shallCreateArraysWithIndex = false;
								break outer;
							}
						}
					}
				} else {
					// Condition to find available places from the end to the beginning.
					outer:
					for (int row = rowRandomly; row > rowRandomly - spaceship.length; row--) {
						for (int column = columnRandomly; column > columnRandomly - spaceship[0].length; column--) {
							if (board[row][column] != null) {
								shallSearchAPlaceInTheBoard = true;
								shallCreateArraysWithIndex = false;
								break outer;
							}
						}
					}
				}
			}catch(ArrayIndexOutOfBoundsException e){
				isArrayIndexOutOfBound = true;
			}
			
			if(!isArrayIndexOutOfBound && shallCreateArraysWithIndex) {
				shallSearchAPlaceInTheBoard = false;
				rowAndColumn[0] = rowRandomly;
				rowAndColumn[1] = columnRandomly;
			}
		}
		
		return rowAndColumn;
	}
	
	private void putSpaceshipAtTheBoard(Match match, String[][] board, String[][] spaceship, int[] rowAndColumn) {
		int rowAvailable = rowAndColumn[0];
		int columnAvailable = rowAndColumn[1];
		int countSpaceshipRow = 0;
		int countSpaceshipColumn = 0;
		int[] positionInTheBoard = new int[2];
		
		if(rowAvailable < columnAvailable) {
			for (int row = rowAvailable; row < rowAvailable + spaceship.length; row++) {
				for (int column = columnAvailable; column < columnAvailable + spaceship[0].length; column++) {
					board[row][column] = spaceship[countSpaceshipRow][countSpaceshipColumn];
					countSpaceshipColumn++;
				}
				countSpaceshipColumn = 0;
				countSpaceshipRow++;
			} 
		} else {
			countSpaceshipRow = spaceship.length-1;
			countSpaceshipColumn = spaceship[0].length-1;
			for (int row = rowAvailable; row > rowAvailable - spaceship.length; row--) {
				for (int column = columnAvailable; column > columnAvailable - spaceship[0].length; column--) {
					board[row][column] = spaceship[countSpaceshipRow][countSpaceshipColumn];
					countSpaceshipColumn--;
				}
				countSpaceshipColumn = spaceship[0].length-1;
				countSpaceshipRow--;
			}
		}
		
//		TODO
//		match.getListOfPositionsInTheBoard().add(rowAndColumn);
	}
	
	private Game createPlayerTurnForStartTheMatch(Match match) {
		Game game = new Game();
		game.setPlayerTurn(match.getUserId());
		return game;
	}
	
	public void fillBoardWithDotsAndCreateArrayForPlayer(Player player, String[][] board) {
		String[] convertedBoard = new String[16];
		String line = "";
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if(board[i][j] == null) {
					board[i][j] = ".";
				}
				line += board[i][j];
			}
			convertedBoard[i] = line;
			line = "";
		}
		
		player.setBoard(convertedBoard);
		player.setBoardForSalvo(board);
	}
	
	private MatchDTO createMatchDTOToReturnForXLSS1(Match match) {
		MatchDTO matchForJSon = new MatchDTO();
		matchForJSon.setUserId(match.getUserId());
		matchForJSon.setFullName(match.getFullName());
		matchForJSon.setGameId(match.getGameId());
		matchForJSon.setStarting(match.getUserId());
		return matchForJSon;
	}
	
	public MatchDTO createMatchDTOToReturnForXLSS2(Match match) {
		MatchDTO matchDTO = new MatchDTO();
		Player self = new Player();
		Player opponent = new Player();
		
		self.setUserId(match.getSelf().getUserId());
		self.setBoard(match.getSelf().getBoard());
		self.setBoardForSalvo(null);
		opponent.setUserId(match.getOpponent().getUserId());
		opponent.setBoard(match.getOpponent().getBoard());
		opponent.setBoardForSalvo(null);
		
		matchDTO.setSelf(self);
		matchDTO.setOpponent(opponent);
		
		if (match.getGame() != null) {
			matchDTO.setGame(match.getGame());
		}
		
		return matchDTO;
	}

	public List<Match> getMatchesList() {
		return matchList;
	}
	
	public Match getFirstMatchBy(String gameId) {
		return matchList.stream().filter(m -> m.getGameId().equals(gameId)).findFirst().orElse(null);
	}
	
	private void printboard(String[][] board) {
		System.out.println();
		for (int row = 0; row < 16; row++) {
			System.out.println();
			for (int column = 0; column < 16; column++) {
				System.out.print(board[row][column] == null ? "_" : board[row][column]);
			}
		}
		System.out.println();
	}

	private void printspaceship(String[][] spaceship) {
		System.out.println();
		for (int row = 0; row < spaceship.length; row++) {
			System.out.println();
			for (int column = 0; column < spaceship[row].length; column++) {
				System.out.print(spaceship[row][column]);
			}
		}
		System.out.println();	
	}
}
