package com.uqbar.vainilla.graphs;


import java.util.ArrayList;
import java.util.List;

public class Node<T extends Valuable> implements Comparable<Node<T>>{
	private T element;
	private String keyName="";
	private List<Edge<T>> adjancencies = new ArrayList<Edge<T>>();
	private double minDistance = Double.POSITIVE_INFINITY;
	private Node<T> previous;
	private double row;
	private double column;
	private boolean visited = false;
	private boolean hasUpAdjacency = false;
	private boolean hasDownAdjacency = false;
	private boolean hasRightAdjacency = false;
	private boolean hasLeftAdjacency = false;
	
	public Node(){
		
	}
	
	public Node(String keyName){
		this.setKeyName(keyName);
	}
	
	public Node(T element, int row, int column)
	{
		this.setElement(element);
		this.setColumn(column);
		this.setRow(row);
		this.setKeyName(row + "-" + column);
	}
	
	public Node(T element, String keyName){
		this.setElement(element);
		this.setKeyName(keyName);
	}
	
	public void addAdjancency(Node<T> node){
		
		int value;
		if(node!=null && node.getElement()!=null){
			value = node.getElement().value();
		}else{
			value=1;
		}
		
		Edge<T> edge = new Edge<T>(this.getKeyName() + "->" + node.getKeyName() , node, value);
		this.getAdjancencies().add(edge);
	}

	public void cleanAdjacencies() {
		this.getAdjancencies().clear();
	}
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public List<Edge<T>> getAdjancencies() {
		return adjancencies;
	}

	public void setAdjancencies(List<Edge<T>> adjancencies) {
		this.adjancencies = adjancencies;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}

	@Override
	public int compareTo(Node<T> other) {
		return Double.compare(minDistance, other.minDistance);
	}
	
	@Override
	public String toString(){
		return this.getKeyName();
	}

	public double getRow() {
		return row;
	}

	public void setRow(double row) {
		this.row = row;
	}

	public double getColumn() {
		return column;
	}

	public void setColumn(double column) {
		this.column = column;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isHasUpAdjacency() {
		return hasUpAdjacency;
	}

	public void setHasUpAdjacency(boolean hasUpAdjacency) {
		this.hasUpAdjacency = hasUpAdjacency;
	}

	public boolean isHasDownAdjacency() {
		return hasDownAdjacency;
	}

	public void setHasDownAdjacency(boolean hasDownAdjacency) {
		this.hasDownAdjacency = hasDownAdjacency;
	}

	public boolean isHasRightAdjacency() {
		return hasRightAdjacency;
	}

	public void setHasRightAdjacency(boolean hasRightAdjacency) {
		this.hasRightAdjacency = hasRightAdjacency;
	}

	public boolean isHasLeftAdjacency() {
		return hasLeftAdjacency;
	}

	public void setHasLeftAdjacency(boolean hasLeftAdjacency) {
		this.hasLeftAdjacency = hasLeftAdjacency;
	}

	

	
}
