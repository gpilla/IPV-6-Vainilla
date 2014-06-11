package com.uqbar.vainilla.graphs;


import java.util.ArrayList;
import java.util.List;

public class Node<T> implements Comparable<Node<T>>{
	private T element;
	private String keyName="";
	private List<Edge<T>> adjancencies = new ArrayList<Edge<T>>();
	private double minDistance = Double.POSITIVE_INFINITY;
	private Node<T> previous;
	
	public Node(){
		
	}
	
	public Node(String keyName){
		this.setKeyName(keyName);
	}
	
	public Node(T element, String keyName){
		this.setElement(element);
		this.setKeyName(keyName);
	}
	
	public void addAdjancency(Node<T> node){
		Edge<T> edge = new Edge<T>(this.getKeyName() + "->" + node.getKeyName() , node, 1);
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

	
}
