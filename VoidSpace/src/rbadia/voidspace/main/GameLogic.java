package rbadia.voidspace.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyShip;
import rbadia.voidspace.model.Ship;
import rbadia.voidspace.sounds.SoundManager;


/**
 * Handles general game logic and status.
 */
public class GameLogic {
	private GameScreen gameScreen;
	private GameStatus status;
	private SoundManager soundMan;
	
	private Ship ship;
	private EnemyShip enemyShip;
	private Asteroid[] asteroid = new Asteroid[5];
	private List<Bullet> bullets;
	private List<Bullet> enemyBullets;
	
	/**
	 * Craete a new game logic handler
	 * @param gameScreen the game screen
	 */
	public GameLogic(GameScreen gameScreen){
		this.gameScreen = gameScreen;
		
		// initialize game status information
		status = new GameStatus();
		// initialize the sound manager
		soundMan = new SoundManager();
		
		// init some variables
		bullets = new ArrayList<Bullet>();
		enemyBullets = new ArrayList<Bullet>();
	}

	/**
	 * Returns the game status
	 * @return the game status 
	 */
	public GameStatus getStatus() {
		return status;
	}

	public SoundManager getSoundMan() {
		return soundMan;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	/**
	 * Prepare for a new game.
	 */
	public void newGame(){
		status.setGameStarting(true);
		
		// init game variables
		bullets = new ArrayList<Bullet>();

		status.setShipsLeft(3);
		status.setGameOver(false);
		status.setAsteroidsDestroyed(0);
		status.setEnemyDestroyed(0);
		status.setNewAsteroid(false, 0); 
		status.setNewAsteroid(false, 1);
		status.setNewAsteroid(false, 2);
		status.setNewAsteroid(false, 3);
		status.setNewAsteroid(false, 4);
		status.setNewEnemy(false);
				
		// init the ship and the asteroid AND THE ENEMY
        newShip(gameScreen);
        newEnemyShip(gameScreen);
        newAsteroid(gameScreen);
        
        // prepare game screen
        gameScreen.doNewGame();
        

        
        // delay to display "Get Ready" message for 1.5 seconds
		Timer timer = new Timer(1500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setGameStarting(false);
				status.setGameStarted(true);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	/**
	 * Check game or level ending conditions.
	 */
	public void checkConditions(){
		// check game over conditions
		if(!status.isGameOver() && status.isGameStarted()){
			if(status.getShipsLeft() == 0){
				gameOver();
			}
		}
	}
	
	/**
	 * Actions to take when the game is over.
	 */
	public void gameOver(){
		status.setGameStarted(false);
		status.setGameOver(true);
		gameScreen.doGameOver();
		
        // delay to display "Game Over" message for 3 seconds
		Timer timer = new Timer(3000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setGameOver(false);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	/**
	 * Fire a bullet from ship.
	 */
	public void fireBullet(){
		Bullet bullet = new Bullet(ship);
		bullets.add(bullet);
		soundMan.playBulletSound();
	}
	
	/**
	 * Plays the game music chosen in loop form.
	 */
	public void playMusic(){
		soundMan.getGameMusic().loop();
	}
	
	
	/**
	 * Fire a bullet from the enemy ship.
	 */
	public void fireEnemyBullet(){
		Bullet enemyBullet = new Bullet(enemyShip);
		enemyBullets.add(enemyBullet);
		soundMan.playBulletSound();
	}
	
	/**
	 * Move a bullet once fired.
	 * @param bullet the bullet to move
	 * @return if the bullet should be removed from screen
	 */
	public boolean moveBullet(Bullet bullet){
		if(bullet.getY() - bullet.getSpeed() >= 0){
			bullet.translate(0, -bullet.getSpeed());
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Move an enemy bullet once fired.
	 * @param enemyBullet the enemyBullet to move
	 * @return if the enemy bullet should be removed from screen
	 */
	public boolean moveEnemyBullet(Bullet enemyBullet){
		if(enemyBullet.getY() - enemyBullet.getEnemyBulletSpeed() >= 0){
			enemyBullet.translate(0, -enemyBullet.getEnemyBulletSpeed());
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Create a new ship (and replace current one).
	 */
	public Ship newShip(GameScreen screen){
		this.ship = new Ship(screen);
		return ship;
	}
	
	/**
	 *Create a new enemy ship
	 */
	public EnemyShip newEnemyShip(GameScreen screen){
		this.enemyShip = new EnemyShip(screen);
		return enemyShip;
	}
	 
	/**
	 * Create a new array of asteroids.
	 */
	public Asteroid[] newAsteroid(GameScreen screen){
		this.asteroid[0] = new Asteroid(screen);
		this.asteroid[1] = new Asteroid(screen);
		this.asteroid[2] = new Asteroid(screen);
		this.asteroid[3] = new Asteroid(screen);
		this.asteroid[4] = new Asteroid(screen);
		return asteroid;
	}
	
	/**
	 * Returns the ship.
	 * @return the ship
	 */
	public Ship getShip() {
		return ship;
	}
	
	/**
	 * Returns the enemy ship.
	 * @return the enemy ship
	 */
	public EnemyShip getEnemyShip() {
		return enemyShip;
	}

	/**
	 * Returns the asteroids.
	 * @return the asteroids
	 */
	public Asteroid[] getAsteroid() {
		return asteroid;
	}

	/**
	 * Returns the list of bullets.
	 * @return the list of bullets
	 */
	public List<Bullet> getBullets() {
		return bullets;
	}
	
	/**
	 * Returns the list of enemy bullets.
	 * @return the list of enemy bullets
	 */
	public List<Bullet> getEnemyBullets() {
		return enemyBullets;
	}
}
