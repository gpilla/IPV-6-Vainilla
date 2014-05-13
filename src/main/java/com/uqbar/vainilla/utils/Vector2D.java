package com.uqbar.vainilla.utils;

public class Vector2D implements Cloneable{

	private double x;
	private double y;
	private double module;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
		this.module =Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
	
	public Vector2D copy() {
		try {
			return (Vector2D) this.clone();
		}
		catch(CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public double angle(){
		return Math.atan2(this.getY(),this.getX());
	}
	
	public void rotate(double deltaRadians){
		double currentAngle = this.angle();
		this.setX(Math.cos(currentAngle + deltaRadians));
		this.setY(Math.sin(currentAngle + deltaRadians));
	}

	@Override
	public String toString() {
		return "("+ this.x + "," + this.y + ")";
	}

	public double getModule() {
		return this.module;
	}
	public void setModule(double module) {
		this.module = module; 
	}
	public Vector2D asVersor(){
		//TODO, usar un delta?
		return this.getModule() != 1 ? new Vector2D(this.x/this.getModule(), this.y/this.getModule()) : this;
	}
	
	public Vector2D producto(double valor){
		return new Vector2D(this.x * valor , this.y * valor);
	}

	public Vector2D suma(Vector2D vector2d) {
		return new Vector2D(this.x + vector2d.getX(), this.y + vector2d.getY());
	}
	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.module);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(this.module) != Double
				.doubleToLongBits(other.module))
			return false;
		if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}
