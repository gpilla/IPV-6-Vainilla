package com.uqbar.vainilla.maps;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

abstract public class AbstractMapParser {

	abstract public Color[][] getTerrainGrid();
	
	/**
	 * Devuelve posiciones donde se pueden spawnear enemigos
	 * 
	 * @return
	 */
	abstract public ArrayList<Double> getEnemySpawnPoints();
	
	abstract public int getWidth();
	
	abstract public int getHeight();
	
}
