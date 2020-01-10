package com.xlspaceship.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
	
	@JsonProperty("user_id")
	private String userId;
	
	private String[] board = new String[16];
	
	private String[][] boardForSalvo = new String[16][16];
	
	private List<int[]> listOfPositionsInTheBoard;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getBoard() {
		return board;
	}

	public void setBoard(String[] board) {
		this.board = board;
	}

	public String[][] getBoardForSalvo() {
		return boardForSalvo;
	}

	public void setBoardForSalvo(String[][] boardForSalvo) {
		this.boardForSalvo = boardForSalvo;
	}

	public List<int[]> getListOfPositionsInTheBoard() {
		return listOfPositionsInTheBoard;
	}

	public void setListOfPositionsInTheBoard(List<int[]> listOfPositionsInTheBoard) {
		this.listOfPositionsInTheBoard = listOfPositionsInTheBoard;
	}
	
	
}
