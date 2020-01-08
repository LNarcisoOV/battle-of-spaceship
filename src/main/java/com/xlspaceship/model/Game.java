package com.xlspaceship.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {

	@JsonProperty("player_turn")
	private String playerTurn;

	@JsonProperty("won")
	private String won;

	public String getWon() {
		return won;
	}

	public void setWon(String won) {
		this.won = won;
	}

	public String getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(String playerTurn) {
		this.playerTurn = playerTurn;
	}

}
