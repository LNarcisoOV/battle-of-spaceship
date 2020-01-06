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
	
	public String[] createBClassRandomly(int randomValue) {
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
	
	public String[] createSClassRandomly(int randomValue) {
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
		String[] aClass = new String[4];
		aClass[0] = ".*.";
		aClass[1] = "*.*";
		aClass[2] = "***";
		aClass[3] = "*.*";
		return aClass;
	}

	private String[] aClassToTheRight() {
		String[] aClass = new String[3];
		aClass[0] = "***.";
		aClass[1] = ".*.*";
		aClass[2] = "***.";
		return aClass;
	}

	private String[] aClassToTheLeft() {
		String[] aClass = new String[3];
		aClass[0] = ".***";
		aClass[1] = "*.*.";
		aClass[2] = ".***";
		return aClass;
	}

	private String[] aClassUpsideDown() {
		String[] aClass = new String[4];
		aClass[0] = "*.*";
		aClass[1] = "***";
		aClass[2] = "*.*";
		aClass[3] = ".*.";
		return aClass;
	}
	
	private String[] normalBClass() {
		String[] bClass = new String[5];
		bClass[0] = "**.";
		bClass[1] = "*.*";
		bClass[2] = "**.";
		bClass[3] = "*.*";
		bClass[4] = "**.";
		return bClass;
	}

	private String[] bClassToTheRight() {
		String[] bClass = new String[3];
		bClass[0] = "*****";
		bClass[1] = "*.*.*";
		bClass[2] = ".*.*.";
		return bClass;
	}

	private String[] bClassToTheLeft() {
		String[] bClass = new String[3];
		bClass[0] = ".*.*.";
		bClass[1] = "*.*.*";
		bClass[2] = "*****";
		return bClass;
	}

	private String[] bClassInverted() {
		String[] bClass = new String[5];
		bClass[0] = ".**";
		bClass[1] = "*.*";
		bClass[2] = ".**";
		bClass[3] = "*.*";
		bClass[4] = ".**";
		return bClass;
	}
	
	private String[] normalSClass() {
		String[] sClass = new String[5];
		sClass[0] = ".**.";
		sClass[1] = "*...";
		sClass[2] = ".**.";
		sClass[3] = "...*";
		sClass[4] = ".**.";
		return sClass;
	}

	private String[] sClassToTheRight() {
		String[] sClass = new String[4];
		sClass[0] = "...*.";
		sClass[1] = "*.*.*";
		sClass[2] = "*.*.*";
		sClass[3] = ".*...";
		return sClass;
	}

	private String[] sClassToTheLeft() {
		String[] sClass = new String[4];
		sClass[0] = ".*...";
		sClass[1] = "*.*.*";
		sClass[2] = "*.*.*";
		sClass[3] = "...*.";
		return sClass;
	}

	private String[] sClassInverted() {
		String[] sClass = new String[5];
		sClass[0] = ".**.";
		sClass[1] = "...*";
		sClass[2] = ".**.";
		sClass[3] = "*...";
		sClass[4] = ".**.";
		return sClass;
	}

}
