package com.uqbar.vainilla.graphs;


public class Edge <T> {
	  private final String id; 
	  private final Node<T> destination;
	  private final int weight; 
	  
	  public Edge(String id, Node<T> destination, int weight) {
	    this.id = id;
	    this.destination = destination;
	    this.weight = weight;
	  }
	  
	  public String getId() {
	    return id;
	  }
	  public Node<T> getDestination() {
	    return destination;
	  }


	  public int getWeight() {
	    return weight;
	  }
	  
}