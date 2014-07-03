package com.uqbar.vainilla.graphs;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.awt.Point;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;

public class MapGraph<T extends Valuable> {
	private Node<T> matrix[][];
	private int rows = 0;
	private int columns = 0;
	private double width = 0;
	private double height = 0;
	private Map<Integer, List<Point>> colorsMap = new HashMap<Integer, List<Point>>();
	
	public MapGraph(){
		
	}
	
	@SuppressWarnings("unchecked")
	public MapGraph(String coloredImagePath){
		BufferedImage coloredImage;
		try {
			coloredImage = ImageIO.read(new ClassLoaderResourcesProvider().getResource(coloredImagePath));
			this.setRows(coloredImage.getHeight());
			this.setColumns(coloredImage.getWidth());
			this.setHeight(coloredImage.getHeight());
			this.setWidth(coloredImage.getWidth());
			this.matrix = (Node<T>[][])Array.newInstance(Node.class, this.getRows(),this.getColumns());
			
			for(int row = 0;row<this.getRows();row++){
				for(int col=0; col<this.getColumns();col++){
					int rgbColor = coloredImage.getRGB(col, row);
					T pixelValuable = (T)new PixelValuable(rgbColor * -1);
					this.addPoint(pixelValuable,row, col);
					Node<T> node = new Node<T>(pixelValuable, row,col);
					this.getMatrix()[row][col] = node;
				}
			}
			this.calcAdjancencies();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void addPoint(Valuable valuable, int row, int column) {
		if(valuable.value()!=16777216)
		{
			List<Point> points = this.getColorsMap().get(valuable.value());
			if(points==null){
				points=new ArrayList<Point>();
				this.getColorsMap().put(valuable.value(), points);
			}
			points.add(new Point(column, row));
		}		
	}

	@SuppressWarnings("unchecked")
	public MapGraph(BufferedImage coloredImage){
		try {
			this.setRows(coloredImage.getHeight());
			this.setColumns(coloredImage.getWidth());
			this.setHeight(coloredImage.getHeight());
			this.setWidth(coloredImage.getWidth());
			this.matrix = (Node<T>[][])Array.newInstance(Node.class, this.getRows(),this.getColumns());
			
			for(int row = 0;row<this.getRows();row++){
				for(int col=0; col<this.getColumns();col++){
					int rgbColor = coloredImage.getRGB(col, row);
					T pixelValuable = (T)new PixelValuable(rgbColor * -1);
					this.addPoint(pixelValuable,row, col);
					Node<T> node = new Node<T>(pixelValuable, row,col);
					this.getMatrix()[row][col] = node;
				}
			}
			this.calcAdjancencies();
			
		} catch (Exception e1) {
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
		for(int row=0; row<this.getRows(); row++)
		{
			for(int column=0; column<this.getColumns(); column++){
				matrix[row][column] = new Node<T>(row + "," + column,row,column);
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
	
		List<Node<T>> teleTransportNodes = new ArrayList<Node<T>>();
		
		for(int row=0; row<this.getRows(); row++)
		{
			for(int col=0; col<this.getColumns(); col++){	
				Node<T> node = this.getMatrix()[row][col];

				if(col>0 && !this.isCellOccupied(row,col-1)){
					node.setHasLeftAdjacency(true);
					node.setLeftAdjacency(this.getMatrix()[row][col-1]);
					node.addAdjancency(this.getMatrix()[row][col-1]);
				}
				if(row>0 && !this.isCellOccupied(row-1,col)){
					node.setHasUpAdjacency(true);
					node.setUpAdjacency(this.getMatrix()[row-1][col]);
					node.addAdjancency(this.getMatrix()[row-1][col]);
				}
				if(col<columns-1 && !this.isCellOccupied(row,col+1)){
					node.setHasRightAdjacency(true);
					node.setRightAdjacency(this.getMatrix()[row][col+1]);
					node.addAdjancency(this.getMatrix()[row][col+1]);
				}
				if(row<rows-1 && !this.isCellOccupied(row+1,col)){
					node.setHasDownAdjacency(true);
					node.setDownAdjacency(this.getMatrix()[row+1][col]);
					node.addAdjancency(this.getMatrix()[row+1][col]);
				}
				if(this.isTeletransportNode(node)){
					teleTransportNodes.add(node);
				}
			}
		}
		
		this.calcTeletransportationNodesAdjacencies(teleTransportNodes);
		
	}
	
	private void calcTeletransportationNodesAdjacencies(List<Node<T>> teletransportNodes){
		Node<T> leftTeletransportNode = this.obtainLeftTeletransport(teletransportNodes);
		Node<T> rightTeletransportNode = this.obtainRightTeletransport(teletransportNodes);
		
		
		if(leftTeletransportNode!=null && rightTeletransportNode!=null){
			leftTeletransportNode.setHasLeftAdjacency(true);
			leftTeletransportNode.getElement().changeValue(1);
			leftTeletransportNode.addAdjancency(rightTeletransportNode);
			leftTeletransportNode.setLeftAdjacency(rightTeletransportNode);
			rightTeletransportNode.setHasRightAdjacency(true);
			rightTeletransportNode.getElement().changeValue(1);
			rightTeletransportNode.addAdjancency(leftTeletransportNode);
			rightTeletransportNode.setRightAdjacency(leftTeletransportNode);
		}
		
	}


	private Node<T> obtainRightTeletransport(List<Node<T>> teletransportNodes) {
		for(Node<T> node : teletransportNodes){
			if(node.getColumn()<this.getColumns()-1 && this.isCellOccupied((int)node.getRow(),(int)node.getColumn()+1)){
				return node;
			}
		}
		return null;
	}

	private Node<T> obtainLeftTeletransport(List<Node<T>> teletransportNodes) {
		for(Node<T> node : teletransportNodes){
			if(node.getColumn()>0 && this.isCellOccupied((int)node.getRow(),(int)node.getColumn()-1)){
				return node;
			}
		}
		return null;
	}

	private boolean isTeletransportNode(Node<T> node) {
		return node!=null && node.getElement()!=null && node.getElement().value()==1237980;
	}

	private boolean isCellOccupied(int i, int j) {
		return this.getMatrix()[i][j]!=null && this.getMatrix()[i][j].getElement()!=null && (this.getMatrix()[i][j].getElement().value()>1 && this.getMatrix()[i][j].getElement().value()!= 1237980);
	}

	public void computePaths(Node<T> sourceNode){
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
    	List<Node<T>> path = new ArrayList<Node<T>>();
    	if(!source.equals(target)){
	    	this.computePaths(source);
	        for (Node<T> vertex = target; vertex != null ; vertex = vertex.getPrevious()){
	        	if(vertex!=source){
	        		path.add(vertex);
	        	}
	        }
	            
	        Collections.reverse(path);
    	}
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
	
	public double obtainRealXPosition(int colNumber){
		return this.obtainHorizontalStep() * colNumber;
	}
	
	public double obtainRealYPosition(int rowNumber){
		return this.obtainVerticalStep() * rowNumber;
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

	public Map<Integer, List<Point>> getColorsMap() {
		return colorsMap;
	}

	public void setColorsMap(Map<Integer, List<Point>> colorsMap) {
		this.colorsMap = colorsMap;
	}	
}
