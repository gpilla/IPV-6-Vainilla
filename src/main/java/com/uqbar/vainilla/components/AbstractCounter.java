package com.uqbar.vainilla.components;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Label;

/**
 * Contador
 * 
 * Por default es de tamaño 20, en negrita y color blanco.
 * 
 * @author Gustavo Pilla <pilla.gustavo@gmail.com>
 *
 * @param <SceneType>
 */
public class AbstractCounter<SceneType extends GameScene> extends GameComponent<SceneType> {
	
	private String description;
	private double value = 0;
	
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	private Color color = Color.WHITE;
	
	@Override
	public void update(DeltaState deltaState) {
		this.setAppearance(getDefaultAppearance());
		super.update(deltaState);
	}

	protected Appearance getDefaultAppearance() {
		return new Label(this.getFont(), this.getColor(), this.getDescription().toUpperCase() + ": " + this.getValue());
	}
	
	// --------------------------------------------------
	// Setters y Getters
	// --------------------------------------------------
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public void addToValue(double points) {
		this.value += points;
	}
	
	public void removeToValue(double points) {
		this.value += points;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}

