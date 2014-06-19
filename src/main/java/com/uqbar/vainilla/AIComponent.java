package com.uqbar.vainilla;

import com.uqbar.vainilla.appearances.Appearance;

public class AIComponent<SceneType extends GameScene> extends GameComponent<SceneType>{
	private double speed = 0;
	
	public AIComponent(){
		super();
	}
	
	public AIComponent(Appearance appearance, double x, double y) {
		super(appearance,x,y);
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	
}
