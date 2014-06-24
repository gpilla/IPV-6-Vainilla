package com.uqbar.vainilla;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Valuable;

public abstract class GraphGameScene extends GameScene{

	private MapGraph<Valuable> mapGraph;
	
	public GraphGameScene(){}
	
	public GraphGameScene(String map){
		this.setMapGraph(new MapGraph<Valuable>(map));
	}
	
	
	public MapGraph<Valuable> getMapGraph() {
		return mapGraph;
	}

	public void setMapGraph(MapGraph<Valuable> mapGraph) {
		this.mapGraph = mapGraph;
	}
	
}
