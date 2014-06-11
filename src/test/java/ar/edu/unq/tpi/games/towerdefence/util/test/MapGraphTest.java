package ar.edu.unq.tpi.games.towerdefence.util.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.VerboseMockitoJUnitRunner;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;

import java.awt.geom.Point2D.Double;
import java.util.List;

@RunWith(VerboseMockitoJUnitRunner.class)
public class MapGraphTest {
	
	@Test
	public void obtainVerticalStep(){
		MapGraph<String> mapGraph = new MapGraph<String>(45,50,500,800);
		double expectedVerticalStep = (double)500/(double)45;
		double actualVerticalStep = mapGraph.obtainVerticalStep();
		assertEquals(expectedVerticalStep, actualVerticalStep,0);
	}
	
	@Test
	public void obtainHorizontalStep(){
		MapGraph<String> mapGraph = new MapGraph<String>(45,50,500,800);
		double expectedVerticalStep = (double)800/(double)50;
		double actualVerticalStep = mapGraph.obtainHorizontalStep();
		assertEquals(expectedVerticalStep, actualVerticalStep,0);
	}
	
	@Test
	public void obtainColNumber(){
		MapGraph<String> mapGraph = new MapGraph<String>(6,6,60,120);
		double x0=0;
		int expectedCol0 = 0;
		int actualCol0 = mapGraph.obtainColNumber(x0);
		assertEquals(expectedCol0, actualCol0);
		
		double x1=5;
		int expectedCol1 = 0;
		int actualCol1 = mapGraph.obtainColNumber(x1);
		assertEquals(expectedCol1, actualCol1);
		
		double x2=10;
		int expectedCol2 = 0;
		int actualCol2 = mapGraph.obtainColNumber(x2);
		assertEquals(expectedCol2, actualCol2);
		
		double x3=20;
		int expectedCol3 = 1;
		int actualCol3 = mapGraph.obtainColNumber(x3);
		assertEquals(expectedCol3, actualCol3);
		
		double x4=70;
		int expectedCol4 = 3;
		int actualCol4 = mapGraph.obtainColNumber(x4);
		assertEquals(expectedCol4, actualCol4);
		
		double x5=100;
		int expectedCol5 = 5;
		int actualCol5 = mapGraph.obtainColNumber(x5);
		assertEquals(expectedCol5, actualCol5);
		
		double x6=110;
		int expectedCol6 = 5;
		int actualCol6 = mapGraph.obtainColNumber(x6);
		assertEquals(expectedCol6, actualCol6);
		
		double x7=120;
		int expectedCol7 = 5;
		int actualCol7 = mapGraph.obtainColNumber(x7);
		assertEquals(expectedCol7, actualCol7);
		
	}

	@Test
	public void obtainRowNumber(){
		MapGraph<String> mapGraph = new MapGraph<String>(6,6,60,120);
		double x0=0;
		int expectedCol0 = 0;
		int actualCol0 = mapGraph.obtainRowNumber(x0);
		assertEquals(expectedCol0, actualCol0);
		
		double x1=5;
		int expectedCol1 = 0;
		int actualCol1 = mapGraph.obtainRowNumber(x1);
		assertEquals(expectedCol1, actualCol1);
		
		double x2=10;
		int expectedCol2 = 1;
		int actualCol2 = mapGraph.obtainRowNumber(x2);
		assertEquals(expectedCol2, actualCol2);
		
		double x3=20;
		int expectedCol3 = 2;
		int actualCol3 = mapGraph.obtainRowNumber(x3);
		assertEquals(expectedCol3, actualCol3);
		
		double x4=60;
		int expectedCol4 = 5;
		int actualCol4 = mapGraph.obtainRowNumber(x4);
		assertEquals(expectedCol4, actualCol4);

	}

	@Test
	public void addNode_WithNotExistentNode(){
		MapGraph<String> mapGraph = new MapGraph<String>(45,50,500,800);
		String expectedNodeElement = "NodeElement";
		boolean result = mapGraph.addNode(25, 18, expectedNodeElement);
		Node<String> actualNode = mapGraph.obtainNode((double)25, (double)18);
		String actualNodeElement = actualNode.getElement();
		assertEquals(true, result);
		assertEquals(expectedNodeElement,actualNodeElement);
	}
	
	@Test
	public void shortestPath_WithNotBlocking(){
		MapGraph<String> mapGraph = new MapGraph<String>(6,6,60,60);
		mapGraph.addNode(5, 35, "NodeElement");
		mapGraph.addNode(15, 35, "NodeElement");
		mapGraph.addNode(25, 35, "NodeElement");
		mapGraph.addNode(35, 35, "NodeElement");
		mapGraph.addNode(45, 35, "NodeElement");
		mapGraph.addNode(5, 15, "NodeElement");
		mapGraph.addNode(15, 15, "NodeElement");
		mapGraph.addNode(25, 15, "NodeElement");
		mapGraph.addNode(35, 15, "NodeElement");
		mapGraph.addNode(55, 15, "NodeElement");
		mapGraph.addNode(5, 25, "NodeElement");
		mapGraph.addNode(15, 25, "NodeElement");
		mapGraph.addNode(25, 25, "NodeElement");
		mapGraph.addNode(35, 25, "NodeElement");
		Node<String> source = mapGraph.obtainNode(0, 0);
		Node<String> destination = mapGraph.obtainNode(5, 5);
		List<Node<String>> path = mapGraph.getShortestPath(source, destination);
		System.out.println(path);

	}
	
	@Test
	public void addNode_WithExistentNode(){
		MapGraph<String> mapGraph = new MapGraph<String>(45,50,500,800);
		String expectedNodeElement = "NodeElement";
		mapGraph.addNode(25, 18, expectedNodeElement);
		boolean result = mapGraph.addNode(25, 18, expectedNodeElement);
		assertEquals(false, result);
	}
	
	@Test
	public void obtainPosition(){
		MapGraph<String> mapGraph = new MapGraph<String>(6,6,60,120);
		Double mousePosition = new Double(25, 45);
		Double expectedPosition = new Double(20, 40);
		Double actualPosition = mapGraph.obtainPosition(mousePosition);
		assertEquals(expectedPosition, actualPosition);
		
	}
	
}
