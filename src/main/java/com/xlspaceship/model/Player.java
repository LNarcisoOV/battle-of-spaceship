package com.xlspaceship.model;

public class Player {
	
	private String userId;
	private String[] board = new String[16];
	
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
	
	
}
