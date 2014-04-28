package com.uqbar.vainilla;

import java.applet.Applet;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Properties;

@SuppressWarnings("serial")
public class GameApplet extends Applet {

	protected GamePlayer player;

	/**
	 * Sobreescribir si no se quiere usar la clase como parametro
	 * 
	 * @return
	 */
	protected Game buildGame() {
		try {
			return (Game) Class.forName(this.getParameter("gameClass"))
					.newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() {
		super.init();
		logStart();
		final GamePlayer player = new GamePlayer(this.buildGame());
		
		this.add(player);
		this.setSize(player.getSize());
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				player.pause();
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				player.resume();
			}
		});
		this.setFocusable(true);
		this.requestFocus();
		this.requestFocusInWindow();
		Thread thread = new Thread(player);
		thread.setPriority(Thread.NORM_PRIORITY+1);
		thread.start();
		
	}

	private void logStart() {
		System.out.println("Vainilla Applet started: " + this.getParameter("gameClass"));
		System.out.println("using opengl= " + System.getProperty("sun.java2d.opengl"));
		Properties sys = System.getProperties();
		for(Object key : sys.keySet()) {
			System.out.println(key.toString() + "="+sys.get(key));
		}
	}

}
