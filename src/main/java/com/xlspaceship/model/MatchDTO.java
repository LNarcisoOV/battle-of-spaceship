package com.xlspaceship.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchDTO {
	
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
	
	
}