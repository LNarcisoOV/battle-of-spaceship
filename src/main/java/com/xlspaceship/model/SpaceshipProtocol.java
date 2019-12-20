package com.xlspaceship.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpaceshipProtocol {
	
	@JsonProperty("hostname")
	private String hostName;
	
	@JsonProperty("port")
	private Integer port;
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
}
