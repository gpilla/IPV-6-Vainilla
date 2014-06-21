package com.uqbar.vainilla;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Invisible;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.utils.ResourceUtil;

public abstract class GameComponent<SceneType extends GameScene> {

	private SceneType scene;
	private Appearance appearance;
	private double x;
	private double y;
	private int z;
	private boolean destroyPending;
	
	//private ArrayList<GameComponentRule<?>> rules;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public GameComponent() {
		this(new Invisible(), 0, 0);
	}

	public GameComponent(double x, double y) {
		this(new Invisible(), x, y);
	}

	public GameComponent(Appearance appearance, double x, double y) {
		this.setAppearance(appearance);
		this.setX(x);
		this.setY(y);
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	public Game getGame() {
		return this.getScene().getGame();
	}

	// ****************************************************************
	// ** INITIALIZATION
	// ****************************************************************

	public void onSceneActivated() {
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	public void move(double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}

	public void destroy() {
		this.setDestroyPending(true);
	}

	// ****************************************************************
	// ** ALIGNMENT OPERATIONS
	// ****************************************************************

	public void alignTopTo(double y) {
		this.move(0, y - this.getY());
	}

	public void alignLeftTo(double x) {
		this.move(x - this.getX(), 0);
	}

	public void alignBottomTo(double y) {
		this.alignTopTo(y + this.getAppearance().getHeight());
	}

	public void alignRightTo(double x) {
		this.alignRightTo(x + this.getAppearance().getWidth());
	}

	public void alignHorizontalCenterTo(double x) {
		this.alignLeftTo(x - this.getAppearance().getWidth() / 2);
	}

	public void alignVerticalCenterTo(double y) {
		this.alignTopTo(y - this.getAppearance().getHeight() / 2);
	}

	public void alignCloserBoundTo(GameComponent<?> target) {
		Appearance ownBounds = this.getAppearance();
		Appearance targetBounds = target.getAppearance();
		double bottomDistance = abs(ownBounds.getHeight() + this.getY() - target.getY());
		double targetRight = target.getX() + targetBounds.getWidth();
		double leftDistance = abs(this.getX() - targetRight);
		double targetBottom = target.getY() + targetBounds.getHeight();
		double topDistance = abs(this.getY() - targetBottom);
		double rightDistance = abs(this.getX() + ownBounds.getWidth() - target.getX());
		double minDistance = min(bottomDistance, min(leftDistance, min(topDistance, rightDistance)));

		if(minDistance == bottomDistance) {
			this.alignBottomTo(target.getY());
		}
		else if(minDistance == leftDistance) {
			this.alignLeftTo(targetRight);
		}
		else if(minDistance == topDistance) {
			this.alignTopTo(targetBottom);
		}
		else {
			this.alignRightTo(target.getX());
		}
	}

	// ****************************************************************
	// ** GAME OPERATIONS
	// ****************************************************************

	public void render(Graphics2D graphics) {
		this.getAppearance().render(this, graphics);
	}

	public void update(DeltaState deltaState) {
		this.getAppearance().update(deltaState.getDelta());
		//this.applyRules(deltaState);
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	public SceneType getScene() {
		return this.scene;
	}

	protected void setScene(SceneType scene) {
		this.scene = scene;
	}

	public Appearance getAppearance() {
		return this.appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getZ() {
		return this.z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public boolean isDestroyPending() {
		return this.destroyPending;
	}

	protected void setDestroyPending(boolean destroyPending) {
		this.destroyPending = destroyPending;
	}
	
	// --------------------------------------------------
	// Metodos auxiliares
	// (Agregados)
	// --------------------------------------------------
	public double getHeight() {
		return this.getAppearance().getHeight();
	}

	public double getWidth() {
		return this.getAppearance().getWidth();
	}

	public Double getCenter() {
		double x = this.getX() + this.getWidth() / 2;
		double y = this.getY() + this.getHeight() / 2;
		return new Double(x, y);
	}
	
	public double getAbsoluteX() {
		return this.getX() + this.getWidth();
	}
	
	public double getAbsoluteY() {
		return this.getY() + this.getHeight();
	}
	
	// --------------------------------------------------------------------------
	// Collision methods
	// --------------------------------------------------------------------------
	
	public boolean collidesWith(Double point) {
		if( this.appearance.getClass() == Rectangle.class ) {
			return CollisionDetector.INSTANCE.collidesPointAgainstSquare(point.getX(), point.getY(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
		return false;
	}
	
	protected double getIntPropertyFromConfig(String resource) {
		return ResourceUtil.getResourceInt(this.getClass().getSimpleName() + "." + resource);
	}
	
	protected String getStringPropertyFromConfig(String resource) {
		return ResourceUtil.getResourceString(this.getClass().getSimpleName() + "." + resource);
	}
	
	// -------------------------------------------------
	// Rules management
	// -------------------------------------------------
	
//	protected ArrayList<GameComponentRule<?>> getRules() {
//		return rules;
//	}
//
//	protected void setRules(ArrayList<GameComponentRule<?>> rules) {
//		this.rules = rules;
//	}
//	
//	protected void addRule(GameComponentRule<?> rule) {
//		this.getRules().add(rule);
//	}
//	
//	protected void removeRule(GameComponentRule<?> rule) {
//		this.getRules().remove(rule);
//	}
//	
//	protected void initRules() {
//		// Nothing yet.
//	}
	
	//protected abstract void applyRules(DeltaState deltaState);

	public void setPosition(Double spawnPoint) {
		this.setX(spawnPoint.getX() - this.getHeight() / 2);
		this.setY(spawnPoint.getY() - this.getWidth() / 2);
	}
	
}