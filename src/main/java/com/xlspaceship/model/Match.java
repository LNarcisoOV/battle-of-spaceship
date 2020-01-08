package com.xlspaceship.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("full_name")
	private String fullName;
	
	@JsonProperty("spaceship_protocol")
	private SpaceshipProtocol spaceshipProtocol;
	
	@JsonProperty("game_id")
	private String gameId;
	
	@JsonProperty("starting")
	private String starting;
	
	@JsonProperty("self")
	private Player self;
	
	@JsonProperty("opponent")
	private Player opponent;
	
	@JsonProperty("game")
	private Game game;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public SpaceshipProtocol getSpaceshipProtocol() {
		return spaceshipProtocol;
	}
	public void setSpaceshipProtocol(SpaceshipProtocol spaceshipProtocol) {
		this.spaceshipProtocol = spaceshipProtocol;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getStarting() {
		return starting;
	}
	public void setStarting(String starting) {
		this.starting = starting;
	}
	public Player getSelf() {
		return self;
	}
	public void setSelf(Player self) {
		this.self = self;
	}
	public Player getOpponent() {
		return opponent;
	}
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
