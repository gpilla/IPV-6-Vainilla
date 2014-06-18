package com.uqbar.vainilla.graphs;

public class PixelValuable implements Valuable {

	private int color=0;
	
	public PixelValuable(int color){
		this.setColor(color);
	}
	
	@Override
	public int value() {
		return this.getColor();
	}

	protected int getColor() {
		return color;
	}

	protected void setColor(int color) {
		this.color = color;
	}

}
