package com.uqbar.vainilla.graphs;

public class BasicValuable implements Valuable {

	private int value = 0;
	
	public BasicValuable(){}
	
	public BasicValuable(int value){
		this.setValue(value);
	}
	
	@Override
	public int value() {
		return this.getValue();
	}

	@Override
	public void changeValue(int value) {
		this.setValue(value);

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
