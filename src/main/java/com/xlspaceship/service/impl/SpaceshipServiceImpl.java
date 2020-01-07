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
	
	public String[][] createAClassRandomly(int randomValue) {
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
	
	public String[][] createBClassRandomly(int randomValue) {
		switch (randomValue) {
			case 0:
				return normalBClass();
			case 1:
				return bClassToTheRight();
			case 2:
				return bClassToTheLeft();
			case 3:
				return bClassInverted();
		}
		return null;
	}
	
	public String[][] createSClassRandomly(int randomValue) {
		switch (randomValue) {
			case 0:
				return normalSClass();
			case 1:
				return sClassToTheRight();
			case 2:
				return sClassToTheLeft();
			case 3:
				return sClassInverted();
		}
		return null;
	}
	
	private String[][]  normalWinger() {
		String[][]  winger = new String[5][3];
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

	private String[][]  wingerToTheSide() {
		String[][]  winger = new String[3][5];
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

	private String[][]  normalAngle() {
		String[][]  angle = new String[4][3];
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

	private String[][]  angleToTheRight() {
		String[][]  angle = new String[3][4];
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
	
	private String[][]  angleToTheLeft() {
		String[][]  angle = new String[3][4];
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
	
	private String[][]  angleUpsideDown() {
		String[][]  angle = new String[4][3];
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
	
	private String[][]  angleInverted() {
		String[][]  angle = new String[4][3];
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
	
	private String[][]  angleUpsideDownInverted() {
		String[][]  angle = new String[4][3];
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
	
	
	private String[][]  normalAClass() {
		String[][] aClass = new String[4][3];
		aClass[0][0] = ".";
		aClass[0][1] = "*";
		aClass[0][2] = ".";
		aClass[1][0] = "*";
		aClass[1][1] = ".";
		aClass[1][2] = "*";
		aClass[2][0] = "*";
		aClass[2][1] = "*";
		aClass[2][2] = "*";
		aClass[3][0] = "*";
		aClass[3][1] = ".";
		aClass[3][2] = "*";
		return aClass;
	}

	private String[][] aClassToTheRight() {
		String[][] aClass = new String[3][4];
		aClass[0][0] = "*";
		aClass[0][1] = "*";
		aClass[0][2] = "*";
		aClass[0][3] = ".";
		aClass[1][0] = ".";
		aClass[1][1] = "*";
		aClass[1][2] = ".";
		aClass[1][3] = "*";
		aClass[2][0] = "*";
		aClass[2][1] = "*";
		aClass[2][2] = "*";
		aClass[2][3] = ".";
		return aClass;
	}

	private String[][] aClassToTheLeft() {
		String[][] aClass = new String[3][4];
		aClass[0][0] = ".";
		aClass[0][1] = "*";
		aClass[0][2] = "*";
		aClass[0][3] = "*";
		aClass[1][0] = "*";
		aClass[1][1] = ".";
		aClass[1][2] = "*";
		aClass[1][3] = ".";
		aClass[2][0] = ".";
		aClass[2][1] = "*";
		aClass[2][2] = "*";
		aClass[2][3] = "*";
		return aClass;
	}

	private String[][] aClassUpsideDown() {
		String[][] aClass = new String[4][3];
		aClass[0][0] = "*";
		aClass[0][1] = ".";
		aClass[0][2] = "*";
		aClass[1][0] = "*";
		aClass[1][1] = "*";
		aClass[1][2] = "*";
		aClass[2][0] = "*";
		aClass[2][1] = ".";
		aClass[2][2] = "*";
		aClass[3][0] = ".";
		aClass[3][1] = "*";
		aClass[3][2] = ".";
		return aClass;
	}
	
	private String[][] normalBClass() {
		String[][] bClass = new String[5][3];
		bClass[0][0] = "*";
		bClass[0][1] = "*";
		bClass[0][2] = ".";
		bClass[1][0] = "*";
		bClass[1][1] = ".";
		bClass[1][2] = "*";
		bClass[2][0] = "*";
		bClass[2][1] = "*";
		bClass[2][2] = ".";
		bClass[3][0] = "*";
		bClass[3][1] = ".";
		bClass[3][2] = "*";
		bClass[4][0] = "*";
		bClass[4][1] = "*";
		bClass[4][2] = ".";
		return bClass;
	}

	private String[][] bClassToTheRight() {
		String[][] bClass = new String[3][5];
		bClass[0][0] = "*";
		bClass[0][1] = "*";
		bClass[0][2] = "*";
		bClass[0][3] = "*";
		bClass[0][4] = "*";
		bClass[1][0] = "*";
		bClass[1][1] = ".";
		bClass[1][2] = "*";
		bClass[1][3] = ".";
		bClass[1][4] = "*";
		bClass[2][0] = ".";
		bClass[2][1] = "*";
		bClass[2][2] = ".";
		bClass[2][3] = "*";
		bClass[2][4] = ".";
		return bClass;
	}

	private String[][] bClassToTheLeft() {
		String[][] bClass = new String[3][5];
		bClass[0][0] = ".";
		bClass[0][1] = "*";
		bClass[0][2] = ".";
		bClass[0][3] = "*";
		bClass[0][4] = ".";
		bClass[1][0] = "*";
		bClass[1][1] = ".";
		bClass[1][2] = "*";
		bClass[1][3] = ".";
		bClass[1][4] = "*";
		bClass[2][0] = "*";
		bClass[2][1] = "*";
		bClass[2][2] = "*";
		bClass[2][3] = "*";
		bClass[2][4] = "*";
		return bClass;
	}

	private String[][] bClassInverted() {
		String[][] bClass = new String[5][3];
		bClass[0][0] = ".";
		bClass[0][1] = "*";
		bClass[0][2] = "*";
		bClass[1][0] = "*";
		bClass[1][1] = ".";
		bClass[1][2] = "*";
		bClass[2][0] = ".";
		bClass[2][1] = "*";
		bClass[2][2] = "*";
		bClass[3][0] = "*";
		bClass[3][1] = ".";
		bClass[3][2] = "*";
		bClass[4][0] = ".";
		bClass[4][1] = "*";
		bClass[4][2] = "*";
		return bClass;
	}
	
	private String[][] normalSClass() {
		String[][] sClass = new String[5][4];
		sClass[0][0] = ".";
		sClass[0][1] = "*";
		sClass[0][2] = "*";
		sClass[0][3] = ".";
		sClass[1][0] = "*";
		sClass[1][1] = ".";
		sClass[1][2] = ".";
		sClass[1][3] = ".";
		sClass[2][0] = ".";
		sClass[2][1] = "*";
		sClass[2][2] = "*";
		sClass[2][3] = ".";
		sClass[3][0] = ".";
		sClass[3][1] = ".";
		sClass[3][2] = ".";
		sClass[3][3] = "*";
		sClass[4][0] = ".";
		sClass[4][1] = "*";
		sClass[4][2] = "*";
		sClass[4][3] = ".";
		return sClass;
	}

	private String[][] sClassToTheRight() {
		String[][] sClass = new String[4][5];
		sClass[0][0] = ".";
		sClass[0][1] = ".";
		sClass[0][2] = ".";
		sClass[0][3] = "*";
		sClass[0][4] = ".";
		sClass[1][0] = "*";
		sClass[1][1] = ".";
		sClass[1][2] = "*";
		sClass[1][3] = ".";
		sClass[1][4] = "*";
		sClass[2][0] = "*";
		sClass[2][1] = ".";
		sClass[2][2] = "*";
		sClass[2][3] = ".";
		sClass[2][4] = "*";
		sClass[3][0] = ".";
		sClass[3][1] = "*";
		sClass[3][2] = ".";
		sClass[3][3] = ".";
		sClass[3][4] = ".";
		return sClass;
	}

	private String[][] sClassToTheLeft() {
		String[][] sClass = new String[4][5];
		sClass[0][0] = ".";
		sClass[0][1] = "*";
		sClass[0][2] = ".";
		sClass[0][3] = ".";
		sClass[0][4] = ".";
		sClass[1][0] = "*";
		sClass[1][1] = ".";
		sClass[1][2] = "*";
		sClass[1][3] = ".";
		sClass[1][4] = "*";
		sClass[2][0] = "*";
		sClass[2][1] = ".";
		sClass[2][2] = "*";
		sClass[2][3] = ".";
		sClass[2][4] = "*";
		sClass[3][0] = ".";
		sClass[3][1] = ".";
		sClass[3][2] = ".";
		sClass[3][3] = "*";
		sClass[3][4] = ".";
		return sClass;
	}

	private String[][] sClassInverted() {
		String[][] sClass = new String[5][4];
		sClass[0][0] = ".";
		sClass[0][1] = "*";
		sClass[0][2] = "*";
		sClass[0][3] = ".";
		sClass[1][0] = ".";
		sClass[1][1] = ".";
		sClass[1][2] = ".";
		sClass[1][3] = "*";
		sClass[2][0] = ".";
		sClass[2][1] = "*";
		sClass[2][2] = "*";
		sClass[2][3] = ".";
		sClass[3][0] = "*";
		sClass[3][1] = ".";
		sClass[3][2] = ".";
		sClass[3][3] = ".";
		sClass[4][0] = ".";
		sClass[4][1] = "*";
		sClass[4][2] = "*";
		sClass[4][3] = ".";
		return sClass;
	}

}
