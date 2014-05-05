package com.uqbar.vainilla;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.utils.Vector2D;

public abstract class MovableComponent<SceneType extends GameScene> extends GameComponent<SceneType>{

	private double speed = 0;
	private double acceleration=0;
	private Vector2D vector;
	
	public MovableComponent(){
		super();
	}
	
	public MovableComponent(Appearance appearance, double x, double y) {
		super(appearance,x,y);
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2D getVector() {
		return vector;
	}

	public void setVector(Vector2D vector) {
		this.vector = vector;
	}
	
	
}
