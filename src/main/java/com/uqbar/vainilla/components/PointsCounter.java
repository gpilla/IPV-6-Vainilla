package com.uqbar.vainilla.components;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;

public class PointsCounter extends GameComponent<GameScene> {
	
	private int points;
	
	public PointsCounter() {
		this.points = 0;
		this.determineAppearance();
	}

	@Override
	public void update(DeltaState deltaState) {
		this.determineAppearance();
		super.update(deltaState);
	}

	private void determineAppearance() {
		this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.WHITE, "Puntos: " + this.getPoints()));
	}

	public int getPoints() {
		return points;
	}
	
	public void addPoint() {
		this.points++;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
}

