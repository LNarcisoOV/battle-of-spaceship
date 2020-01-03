package com.xlspaceship.service.impl;

import org.springframework.stereotype.Service;

import com.xlspaceship.service.SpaceshipService;

@Service
public class SpaceshipServiceImpl implements SpaceshipService{
	public String[] createWingerRandomly(int randomValue) {
		switch (randomValue) {
			case 0:
				return normalWinger();
			case 1:
				return wingerToTheSide();
		}
		return null;
	}
	
	public String[] createAngleRandomly(int randomValue) {
		switch (randomValue) {
			case 0:
				return normalAngle();
			case 1:
				return angleToTheRight();
			case 2:
				return angleToTheLeft();
			case 3:
				return angleUpsideDown();
			case 4:
				return angleUpsideDownInverted();
			case 5:
				return angleInverted();
		}
		return null;
	}
	
	public String[] createAClassRandomly(int randomValue) {
		switch (randomValue) {
			case 0:
				return normalAClass();
			case 1:
				return aClassToTheRight();
			case 2:
				return aClassToTheLeft();
			case 3:
				return aClassUpsideDown();
		}
		return null;
	}
	
	private String[] normalWinger() {
		String[] winger = new String[5];
		winger[0] = "*.*";
		winger[1] = "*.*";
		winger[2] = ".*.";
		winger[3] = "*.*";
		winger[4] = "*.*";
		return winger;
	}

	private String[] wingerToTheSide() {
		String[] winger = new String[3];
		winger[0] = "**.**";
		winger[1] = "..*..";
		winger[2] = "**.**";
		return winger;
	}

	private String[] normalAngle() {
		String[] angle = new String[4];
		angle[0] = "*..";
		angle[1] = "*..";
		angle[2] = "*..";
		angle[3] = "***";
		return angle;
	}

	private String[] angleToTheRight() {
		String[] angle = new String[3];
		angle[0] = "****";
		angle[1] = "*...";
		angle[2] = "*...";
		return angle;
	}
	
	private String[] angleToTheLeft() {
		String[] angle = new String[3];
		angle[0] = "****";
		angle[1] = "...*";
		angle[2] = "...*";
		return angle;
	}
	
	private String[] angleUpsideDown() {
		String[] angle = new String[4];
		angle[0] = "***";
		angle[1] = "*..";
		angle[2] = "*..";
		angle[3] = "*..";
		return angle;
	}
	
	private String[] angleInverted() {
		String[] angle = new String[4];
		angle[0] = "..*";
		angle[1] = "..*";
		angle[2] = "..*";
		angle[3] = "***";
		return angle;
	}
	
	private String[] angleUpsideDownInverted() {
		String[] angle = new String[4];
		angle[0] = "***";
		angle[1] = "..*";
		angle[2] = "..*";
		angle[3] = "..*";
		return angle;
	}
	
	private String[] normalAClass() {
		String[] angle = new String[4];
		angle[0] = ".*.";
		angle[1] = "*.*";
		angle[2] = "***";
		angle[3] = "*.*";
		return angle;
	}

	private String[] aClassToTheRight() {
		String[] angle = new String[3];
		angle[0] = "***.";
		angle[1] = ".*.*";
		angle[2] = "***.";
		return angle;
	}

	private String[] aClassToTheLeft() {
		String[] angle = new String[3];
		angle[0] = ".***";
		angle[1] = "*.*.";
		angle[2] = ".***";
		return angle;
	}

	private String[] aClassUpsideDown() {
		String[] angle = new String[4];
		angle[0] = "*.*";
		angle[1] = "***";
		angle[2] = "*.*";
		angle[3] = ".*.";
		return angle;
	}

}
