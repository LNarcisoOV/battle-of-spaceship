package com.xlspaceship.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlspaceship.model.Match;
import com.xlspaceship.model.Player;
import com.xlspaceship.service.MatchService;
import com.xlspaceship.service.SpaceshipService;

@Service
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	private SpaceshipService spaceshipService;

	private List<Match> matchList = new ArrayList<Match>();
	
	private Random random = new Random();

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
		
		Player self = createPlayer(match);
		
		return match;
	}

	private Player createPlayer(Match match) {
		Player player = new Player();
		player.setUserId(match.getUserId());
		
		createBoardAndSpaceships(player);
		
		return player;
	}

	private void createBoardAndSpaceships(Player player) {
		String[][] board = new String[16][16];
		
		putWingerAtTheBoard(board);
		putAngleAtTheBoard(board);
		putAClassAtTheBoard(board);
		putBClassAtTheBoard(board);
		putSClassAtTheBoard(board);
	}

	private void putWingerAtTheBoard(String[][] board) {
		String[][] winger = spaceshipService.createWingerRandomly(random.nextInt(2));
		putSpaceshipAtTheBoard(board, winger);
	}
	
	private void putAngleAtTheBoard(String[][] board) {
		String[][] angle = spaceshipService.createAngleRandomly(random.nextInt(6));
		putSpaceshipAtTheBoard(board, angle);
	}
	
	private void putAClassAtTheBoard(String[][] board) {
		String[][] aClass = spaceshipService.createAClassRandomly(random.nextInt(4));
		putSpaceshipAtTheBoard(board, aClass);
	}
	
	private void putBClassAtTheBoard(String[][] board) {
		String[][] bClass = spaceshipService.createBClassRandomly(random.nextInt(4));
		putSpaceshipAtTheBoard(board, bClass);
	}
	
	private void putSClassAtTheBoard(String[][] board) {
		String[][] sClass = spaceshipService.createSClassRandomly(random.nextInt(4));
		putSpaceshipAtTheBoard(board, sClass);
	}
	
	private void putSpaceshipAtTheBoard(String[][] board, String[][] spaceship) {
		int[] rowAndColumnAvailable = findAnAvailablePlaceInTheBoard(board, spaceship);
		putSpaceshipAtTheBoard(board, spaceship, rowAndColumnAvailable);
	}

	private void putSpaceshipAtTheBoard(String[][] board, String[][] spaceship, int[] rowAndColumn) {
		int rowAvailable = rowAndColumn[0];
		int columnAvailable = rowAndColumn[1];
		int countSpaceshipRow = 0;
		int countSpaceshipColumn = 0;
		
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

	
	
	
	
	

	public List<Match> getMatchList() {
		return matchList;
	}
	
	
	
	
	private void printboard(String[][] board) {
		// TODO COMENTT
		System.out.println();
		for (int row = 0; row < 16; row++) {
			System.out.println();
			for (int column = 0; column < 16; column++) {
				System.out.print(board[row][column] == null ? "." : board[row][column]);
			}
		}
		System.out.println();
	}

	private void printspaceship(String[][] spaceship) {
		System.out.println();

		// TODO COMENTT
		for (int row = 0; row < spaceship.length; row++) {
			System.out.println();
			for (int column = 0; column < spaceship[row].length; column++) {
				System.out.print(spaceship[row][column]);
			}
		}
		System.out.println();	
	}
	
	
	
	
}
