package com.uqbar.vainilla.utils;

import java.applet.Applet;
import java.net.URL;

/**
 * Usado para obtener resources desde la página web donde se corre el applet
 * @author leo
 */
public class AppletResourceProvider implements ResourceProvider {

	private Applet applet;
	public AppletResourceProvider(Applet applet) {
		this.applet = applet;
	}
	@Override
	public URL getResource(String name) throws Exception {
	    return new URL(applet.getCodeBase(), name);
	}

}
