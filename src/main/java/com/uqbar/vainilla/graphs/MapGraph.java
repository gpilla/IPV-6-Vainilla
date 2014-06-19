package com.uqbar.vainilla.graphs;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class MapGraph<T extends Valuable> {
	private Node<T> matrix[][];
	private int rows = 0;
	private int columns = 0;
	private double width = 0;
	private double height = 0;

	
	public MapGraph(){
		
	}
	
	@SuppressWarnings("unchecked")
	public MapGraph(String coloredImagePath){
		BufferedImage coloredImage;
		try {
			coloredImage = ImageIO.read(this.getClass().getClassLoader().getResource(coloredImagePath));
			this.setRows(coloredImage.getHeight());
			this.setColumns(coloredImage.getWidth());
			this.setHeight(coloredImage.getHeight());
			this.setWidth(coloredImage.getWidth());
			this.matrix = (Node<T>[][])Array.newInstance(Node.class, this.getRows(),this.getColumns());
			
			for(int row = 0;row<this.getRows();row++){
				for(int col=0; col<this.getColumns();col++){
					int rgbColor = coloredImage.getRGB(col, row);
					T pixelValuable = (T)new PixelValuable(rgbColor * -1);
					Node<T> node = new Node<T>(pixelValuable, row + "-" + col);
					this.getMatrix()[row][col] = node;
				}
			}
			this.calcAdjancencies();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	

	public MapGraph(int rows, int columns, double height, double width){
		this.setRows(rows);
		this.setColumns(columns);
		this.setWidth(width);
		this.setHeight(height);
		this.matrix = this.initMatrix();
	}
	
	@SuppressWarnings("unchecked")
	private Node<T>[][] initMatrix() {
		this.matrix = (Node<T>[][])Array.newInstance(Node.class, this.getRows(),this.getColumns());
		for(int i=0; i<this.getRows(); i++)
		{
			for(int j=0; j<this.getColumns(); j++){
				matrix[i][j] = new Node<T>(i + "," + j);
			}
		}
		this.calcAdjancencies();
		return matrix;
		
	}

	private void calcAdjancencies() {
	
		for(int i=0; i<this.getRows(); i++)
		{
			for(int j=0; j<this.getColumns(); j++){
				Node<T> node = this.getMatrix()[i][j];
				if(node!=null){
					node.cleanAdjacencies();
				}
			}
		}
	
		for(int i=0; i<this.getRows(); i++)
		{
			for(int j=0; j<this.getColumns(); j++){
				Node<T> node = this.getMatrix()[i][j];
				if(i==70 && j==70){
					node = this.getMatrix()[i][j];
				}
				if(j>0 && !this.isCellOccupied(i,j-1)){
					node.addAdjancency(this.getMatrix()[i][j-1]);
				}
				if(i>0 && !this.isCellOccupied(i-1,j)){
					node.addAdjancency(this.getMatrix()[i-1][j]);
				}
				if(j<columns-1 && !this.isCellOccupied(i,j+1)){
					node.addAdjancency(this.getMatrix()[i][j+1]);
				}
				if(i<rows-1 && !this.isCellOccupied(i+1,j)){
					node.addAdjancency(this.getMatrix()[i+1][j]);
				}
			}
		}
	}

	private boolean isCellOccupied(int i, int j) {
		return this.getMatrix()[i][j]!=null && this.getMatrix()[i][j].getElement()!=null && this.getMatrix()[i][j].getElement().value()>1;
	}

	private void computePaths(Node<T> sourceNode){
		PriorityQueue<Node<T>> nodeQueue = new PriorityQueue<Node<T>>();
		sourceNode.setMinDistance(0);
		nodeQueue.add(sourceNode);
		while (!nodeQueue.isEmpty()) {
			Node<T> node = nodeQueue.poll();

            for (Edge<T> e : node.getAdjancencies())
            {
            	Node<T> target = e.getDestination();
                double weight = e.getWeight();
                double distanceThroughNode = node.getMinDistance() + weight;
				if (distanceThroughNode < target.getMinDistance()) {
				    nodeQueue.remove(target);
				    target.setMinDistance(distanceThroughNode);
				    target.setPrevious(node);
				    nodeQueue.add(target);
				}
            }
       }
	}
	
    public List<Node<T>> getShortestPath(Node<T> source,Node<T> target)
    {
    	this.computePaths(source);
        List<Node<T>> path = new ArrayList<Node<T>>();
        for (Node<T> vertex = target; vertex != null; vertex = vertex.getPrevious())
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
	
	public boolean addNode(double x, double y, T element){
		int col = this.obtainColNumber(x);
		int row = this.obtainRowNumber(y);
		Node<T> node = this.getMatrix()[row][col];
		if(node==null || node.getElement()==null  ){
			node = new Node<T>(element, row + "-" + col);
			this.getMatrix()[row][col] = node;
			this.calcAdjancencies();
			return true;
		}else{
			return false;
		}
	}
	
	
	public Node<T> obtainNode(double x, double y){
		int col = this.obtainColNumber(x);
		int row = this.obtainRowNumber(y);
		return this.getMatrix()[row][col];
	}
	
	public Node<T> obtainNode(int row, int col){
		return this.getMatrix()[row][col];
	}
	
	
	public int obtainColNumber(double x){
		if(x>=this.getWidth()){
			return this.getColumns() - 1;
		}
		return (int)(x/this.obtainHorizontalStep());
	}

	public int obtainRowNumber(double y){
		if(y>=this.getHeight()){
			return this.getRows()-1;
		}
		return (int)(y/this.obtainVerticalStep());
	}
	
	public double obtainVerticalStep(){
		return this.getHeight()/this.getRows();
	}
	
	
	public Double obtainPosition(Double mousePosition){
		double horizontalStep = this.obtainHorizontalStep();
		double verticalStep = this.obtainVerticalStep();
		int colNumber = this.obtainColNumber(mousePosition.x);
		double x = ((double)colNumber)*horizontalStep;
		int rowNumber = this.obtainRowNumber(mousePosition.y);
		double y = ((double)rowNumber)*verticalStep;
		return new Double(x, y);
	}
	

	
	
	public double obtainHorizontalStep(){
		return this.getWidth()/this.getColumns();
	}
	
	protected int getColumns() {
		return columns;
	}

	protected void setColumns(int columns) {
		this.columns = columns;
	}

	protected int getRows() {
		return rows;
	}

	protected void setRows(int rows) {
		this.rows = rows;
	}

	protected double getWidth() {
		return width;
	}
	protected void setWidth(double width) {
		this.width = width;
	}
	protected double getHeight() {
		return height;
	}
	protected void setHeight(double height) {
		this.height = height;
	}

	public Node<T>[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Node<T>[][] matrix) {
		this.matrix = matrix;
	}
	
	
}
