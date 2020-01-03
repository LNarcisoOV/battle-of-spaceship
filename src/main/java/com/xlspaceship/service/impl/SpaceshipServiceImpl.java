package com.xlspaceship.service.impl;

import org.springframework.stereotype.Service;

import com.xlspaceship.service.SpaceshipService;

@Service
public class SpaceshipServiceImpl implements SpaceshipService{
	public String[][] createWingerRandomly(int randomValue) {
		switch (randomValue) {
			case 0:
				return normalWinger();
			case 1:
				return wingerToTheSide();
		}
		return null;
	}
	
	public String[][] createAngleRandomly(int randomValue) {
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
	
	private String[][] normalWinger() {
		String[][] winger = new String[5][3];
		winger[0][0] = "*";
		winger[0][1] = ".";
		winger[0][2] = "*";
		winger[1][0] = "*";
		winger[1][1] = ".";
		winger[1][2] = "*";
		winger[2][0] = ".";
		winger[2][1] = "*";
		winger[2][2] = ".";
		winger[3][0] = "*";
		winger[3][1] = ".";
		winger[3][2] = "*";
		winger[4][0] = "*";
		winger[4][1] = ".";
		winger[4][2] = "*";
		return winger;
	}

	private String[][] wingerToTheSide() {
		String[][] winger = new String[3][5];
		winger[0][0] = "*";
		winger[0][1] = "*";
		winger[0][2] = ".";
		winger[0][3] = "*";
		winger[0][4] = "*";
		winger[1][0] = ".";
		winger[1][1] = ".";
		winger[1][2] = "*";
		winger[1][3] = ".";
		winger[1][4] = ".";
		winger[2][0] = "*";
		winger[2][1] = "*";
		winger[2][2] = ".";
		winger[2][3] = "*";
		winger[2][4] = "*";
		return winger;
	}

	private String[][] normalAngle() {
		String[][] angle = new String[4][3];
		angle[0][0] = "*";
		angle[0][1] = ".";
		angle[0][2] = ".";
		angle[1][0] = "*";
		angle[1][1] = ".";
		angle[1][2] = ".";
		angle[2][0] = "*";
		angle[2][1] = ".";
		angle[2][2] = ".";
		angle[3][0] = "*";
		angle[3][1] = "*";
		angle[3][2] = "*";
		return angle;
	}

	private String[][] angleToTheRight() {
		String[][] angle = new String[3][4];
		angle[0][0] = "*";
		angle[0][1] = "*";
		angle[0][2] = "*";
		angle[0][3] = "*";
		angle[1][0] = "*";
		angle[1][1] = ".";
		angle[1][2] = ".";
		angle[1][3] = ".";
		angle[2][0] = "*";
		angle[2][1] = ".";
		angle[2][2] = ".";
		angle[2][3] = ".";
		return angle;
	}
	
	private String[][] angleToTheLeft() {
		String[][] angle = new String[3][4];
		angle[0][0] = "*";
		angle[0][1] = "*";
		angle[0][2] = "*";
		angle[0][3] = "*";
		angle[1][0] = ".";
		angle[1][1] = ".";
		angle[1][2] = ".";
		angle[1][3] = "*";
		angle[2][0] = ".";
		angle[2][1] = ".";
		angle[2][2] = ".";
		angle[2][3] = "*";
		return angle;
	}
	
	private String[][] angleUpsideDown() {
		String[][] angle = new String[4][3];
		angle[0][0] = "*";
		angle[0][1] = "*";
		angle[0][2] = "*";
		angle[1][0] = "*";
		angle[1][1] = ".";
		angle[1][2] = ".";
		angle[2][0] = "*";
		angle[2][1] = ".";
		angle[2][2] = ".";
		angle[3][0] = "*";
		angle[3][1] = ".";
		angle[3][2] = ".";
		return angle;
	}
	
	private String[][] angleInverted() {
		String[][] angle = new String[4][3];
		angle[0][0] = ".";
		angle[0][1] = ".";
		angle[0][2] = "*";
		angle[1][0] = ".";
		angle[1][1] = ".";
		angle[1][2] = "*";
		angle[2][0] = ".";
		angle[2][1] = ".";
		angle[2][2] = "*";
		angle[3][0] = "*";
		angle[3][1] = "*";
		angle[3][2] = "*";
		return angle;
	}
	
	private String[][] angleUpsideDownInverted() {
		String[][] angle = new String[4][3];
		angle[0][0] = "*";
		angle[0][1] = "*";
		angle[0][2] = "*";
		angle[1][0] = ".";
		angle[1][1] = ".";
		angle[1][2] = "*";
		angle[2][0] = ".";
		angle[2][1] = ".";
		angle[2][2] = "*";
		angle[3][0] = ".";
		angle[3][1] = ".";
		angle[3][2] = "*";
		return angle;
	}

}
