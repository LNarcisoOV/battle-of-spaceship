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
		Match match = getMatchBy(gameId);
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
		player.setListOfPositionsInTheBoard(new ArrayList<>());
		
		String[][] winger = spaceshipService.createWingerRandomly(random.nextInt(2));
		String[][] angle = spaceshipService.createAngleRandomly(random.nextInt(6));
		String[][] aClass = spaceshipService.createAClassRandomly(random.nextInt(4));
		String[][] bClass = spaceshipService.createBClassRandomly(random.nextInt(4));
		String[][] sClass = spaceshipService.createSClassRandomly(random.nextInt(4));
		
		findAvailablePlaceAndPutSpaceAtTheBoard(match, player, winger, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, player, angle, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, player, aClass, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, player, bClass, board);
		findAvailablePlaceAndPutSpaceAtTheBoard(match, player, sClass, board);
		fillBoardWithDotsAndCreateArrayForPlayer(player, board);
	}

	private void findAvailablePlaceAndPutSpaceAtTheBoard(Match match, Player player, String[][] spaceship, String[][] board) {
		int[] rowAndColumnAvailable = findAnAvailablePlaceInTheBoard(board, spaceship);
		putSpaceshipAtTheBoard(match, player, board, spaceship, rowAndColumnAvailable);
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
					outerLoop:
					for (int row = rowRandomly; row < rowRandomly + spaceship.length; row++) {
						for (int column = columnRandomly; column < columnRandomly + spaceship[0].length; column++) {
							if (board[row][column] != null) {
								shallSearchAPlaceInTheBoard = true;
								shallCreateArraysWithIndex = false;
								break outerLoop;
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
	
	private void putSpaceshipAtTheBoard(Match match, Player player, String[][] board, String[][] spaceship, int[] rowAndColumn) {
		int rowAvailable = rowAndColumn[0];
		int columnAvailable = rowAndColumn[1];
		int countSpaceshipRow = 0;
		int countSpaceshipColumn = 0;
		
//		Storing the position in the board for this spaceship
//		to check if the spaceship was killed ahead
		int[] positionInTheBoard = new int[4];
		
		if(rowAvailable < columnAvailable) {
			positionInTheBoard[0] = rowAvailable;
			positionInTheBoard[1] = columnAvailable;
			positionInTheBoard[2] = rowAvailable + spaceship.length -1;
			positionInTheBoard[3] = columnAvailable + spaceship[0].length -1;
			
			for (int row = rowAvailable; row < rowAvailable + spaceship.length; row++) {
				for (int column = columnAvailable; column < columnAvailable + spaceship[0].length; column++) {
					board[row][column] = spaceship[countSpaceshipRow][countSpaceshipColumn];
					countSpaceshipColumn++;
				}
				countSpaceshipColumn = 0;
				countSpaceshipRow++;
			} 
		} else {
			positionInTheBoard[0] = rowAvailable - spaceship.length + 1 ;;
			positionInTheBoard[1] = columnAvailable - spaceship[0].length + 1 ;;
			positionInTheBoard[2] = rowAvailable;
			positionInTheBoard[3] = columnAvailable;
			
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
		
		player.getListOfPositionsInTheBoard().add(positionInTheBoard);
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
		
		if(match.getSelf() != null) {
			self.setUserId(match.getSelf().getUserId());
			self.setBoard(match.getSelf().getBoard());
			self.setBoardForSalvo(null);
			matchDTO.setSelf(self);
		}
		
		if(match.getOpponent() != null) {
			opponent.setUserId(match.getOpponent().getUserId());
			opponent.setBoard(match.getOpponent().getBoard());
			opponent.setBoardForSalvo(null);
			matchDTO.setOpponent(opponent);
		}
		
		if (match.getGame() != null) {
			matchDTO.setGame(match.getGame());
		}
		
		return matchDTO;
	}

	public List<Match> getMatchesList() {
		return matchList;
	}
	
	public Match getMatchBy(String gameId) {
		return matchList.stream().filter(m -> m.getGameId().equals(gameId)).findFirst().orElse(null);
	}
}
