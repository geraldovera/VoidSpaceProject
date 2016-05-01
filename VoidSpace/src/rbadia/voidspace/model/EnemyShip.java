package rbadia.voidspace.model;

import java.awt.Rectangle;

import rbadia.voidspace.main.GameScreen;

/**
 * Represents a ship/space craft.
 *
 */
public class EnemyShip extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 5;
	private static final int ENEMY_Y_OFFSET = 5; // initial y distance of the ship from the bottom of the screen 
	
	private int enemyShipWidth = 32;
	private int enemyShipHeight = 32;
	private int speed = DEFAULT_SPEED;
	
	/**
	 * Creates a new enemy ship at the default initial location. 
	 * @param screen the game screen
	 */
	public EnemyShip(GameScreen screen){
		this.setLocation((screen.getWidth() - enemyShipWidth)/2,
				enemyShipHeight + ENEMY_Y_OFFSET);
		this.setSize(enemyShipWidth, enemyShipHeight);
	}
	
	/**
	 * Get the default ship width
	 * @return the default ship width
	 */
	public int getEnemyShipWidth() {
		return enemyShipWidth;
	}
	
	/**
	 * Get the default ship height
	 * @return the default ship height
	 */
	public int getEnemyShipHeight() {
		return enemyShipHeight;
	}
	
	/**
	 * Returns the current ship speed
	 * @return the current ship speed
	 */
	public int getEnemySpeed() {
		return speed;
	}
	
	/**
	 * Set the current ship speed
	 * @param speed the speed to set
	 */
	public void setEnemySpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Returns the default ship speed.
	 * @return the default ship speed
	 */
	public int getEnemyDefaultSpeed(){
		return DEFAULT_SPEED;
	}
	
}
