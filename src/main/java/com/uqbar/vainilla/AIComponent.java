package com.uqbar.vainilla;

import java.awt.Point;

import com.uqbar.vainilla.appearances.Appearance;

public abstract class AIComponent<SceneType extends GraphGameScene> extends GameComponent<SceneType>{
	private double speed = 0;
	private Point destination = new Point();
	
	public AIComponent(){
		super();
	}
	
	public AIComponent(Appearance appearance, double x, double y) {
		super(appearance,x,y);
	}
	
	public void setNewDestination(int x, int y){
		this.getDestination().setLocation(x,y);
	}
	
	
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Point getDestination() {
		return destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}
	
	
}
