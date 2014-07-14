package com.uqbar.vainilla.maps;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.exceptions.GameException;
import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;
import com.uqbar.vainilla.utils.ResourceProvider;

abstract public class AbstractImageMapParser extends AbstractMapParser {

	public static ResourceProvider defaultResourceProvider = new ClassLoaderResourcesProvider();
	BufferedImage image;
	
	public AbstractImageMapParser(String file) {
		try {
			image = ImageIO.read(defaultResourceProvider.getResource(file));
		} catch (Exception e) {
			throw new GameException("The resource '" + file
					+ "' was not found using " + defaultResourceProvider.getClass().getName(), e);
		}
//		System.out.println("Altura" + this.getHeight() );
//		System.out.println("Anchura" + this.getWidth() );
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public Color getColor(int x, int y) {
		Color color = null;
		try {
			color = new Color(image.getRGB(x, y));	
		} catch (Exception e) {
			System.out.println(x + ";" + y);
		}
		return color;
		
	}
	
	/**
	 * Devuelve el color en un forato hexadecimal similar al utilizado en HTML
	 * 
	 * No pregunten por que, me sentia mas comodo así!
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public String getHexColor(int x, int y) {
		Color color = this.getColor(x, y);
		return "#" + Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen()) + Integer.toHexString(color.getBlue());
	}
	
	@Override
	public Color[][] getTerrainGrid() {
		Color[][] terrainGrid = new Color[this.getHeight()][this.getWidth()];
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				terrainGrid[i][j] = this.getColor(j,i );
			}
		}
		return terrainGrid;
	}
	
}
