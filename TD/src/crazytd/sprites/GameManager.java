/*
 * Copyright (c) 2013 Jimmy Zhang
 * See the file COPYING.txt for full copyright information
 */

package crazytd.sprites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Renderable;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.sdk.graphics.Vector;
import org.robobrain.test.TDspriteGame;

import crazytd.map.Block;
import crazytd.map.Castle;
import crazytd.map.Map;
import crazytd.map.MonsterDen;
import crazytd.map.Road;

import android.util.Log;

/**
 * Manages the towers, monsters, and missiles, as well as their interactions with each other in the game.
 * 
 * @author Jimmy
 */
public class GameManager  {

	/**
	 * Size length of one block, used for testing/debugging
	 */
	public final static int TILE_SIZE = 64;

	/**
	 * This is for setting MonsterDen.timeInterval, testing/debugging use
	 */
	public final static int MONSTER_CREATION_INTERVAL = 1000;

	/**
	 * The maximum hp for the castle, used for testing and debugging
	 */
	public final static int CASTLE_HP = 1;

	// For tracking elapsedTime for MonsterDen and Towers
	private long startTime;
	private long endTime;
	private long deltaTime;

	private List<Tower> towers;
	private List<Monster> monsters;
	private List<Missile> missiles;

	private Map map;
	private MonsterDen monsterDen;
	private Castle castle;

	private World world;

	public boolean isGameOver = false;


	public GameManager(World world) {
		this.world = world;
		towers = new ArrayList<Tower>();
		monsters = new ArrayList<Monster>();
		missiles = new ArrayList<Missile>();
		startTime = System.currentTimeMillis();

	}


	/**
	 * Updates the lists of sprites
	 */
	public void updateGame(){
		
		if (isGameOver) {
			return;
		}
		
		endTime = System.currentTimeMillis();
		deltaTime = endTime - startTime;
		startTime = System.currentTimeMillis();

		updateMonsterDen();
		updateMissiles();
		updateMonsters();
		updateTowers();


	}

	/**
	 * updates elapsedTime for the MonsterDen. 
	 * Sends out monsters periodically
	 */
	private void updateMonsterDen(){
		
		if (monsterDen.getElapsedTime() < MONSTER_CREATION_INTERVAL){
			monsterDen.setElapsedTime(monsterDen.getElapsedTime() + deltaTime);
		}
		
		// if monsterDen is not sending monsters, then we do not create new monsters
		if (!monsterDen.isSendingMonsters()){
			return;
		}
		
		if (monsterDen.getElapsedTime() >= MONSTER_CREATION_INTERVAL){
			monsterDen.setElapsedTime(0);
			Monster newMonster = map.getMonster().clone();
			newMonster.setX(monsterDen.getX());
			newMonster.setY(monsterDen.getY());
			newMonster.setDirection(monsterDen.getDirection(newMonster.x, newMonster.y));
			addMonster(newMonster);
		}
	}

	/**
	 * Updates elapsedTime for each tower. 
	 * Updates each tower's target monster. 
	 * Fires missile if frequency is reached
	 */
	private void updateTowers(){
		for (Tower tower : towers) {

			updateTowerElapsedTime(tower);
			updateTowerTarget(tower);

			if ((tower.getElapsedTime() >= tower.getFiringInterval()) && (tower.target != null)){
				fireMissile(tower);
			}
		}
	}

	/**
	 * Creates a new missile from the tower. 
	 * Adds missile to list
	 * @param tower
	 */
	private void fireMissile(Tower tower){
		tower.setElapsedTime(0);
		Missile newMissile = tower.getMissile().clone();
		newMissile.setTarget(tower.getTarget());
		newMissile.x = tower.x; newMissile.y = tower.y;
		addMissile(newMissile);
	}

	/**
	 * adds deltaTime to tower's elapsedTime.
	 * @param tower
	 */
	private void updateTowerElapsedTime(Tower tower){
		if (tower.getElapsedTime() < tower.getFiringInterval()) {
			tower.setElapsedTime(tower.getElapsedTime() + deltaTime);
		}
	}

	/**
	 * checks if target is still in range, if not then deletes the current target. 
	 * Sets a target if it doesn't already have one
	 */
	private void updateTowerTarget(Tower tower){

		// Deletes the current target if the target is out of range
		if (tower.target != null){
			float actualRange = tower.range * TILE_SIZE;
			if (tower.distToMonster(tower.target) > actualRange){
				tower.target = null;
			}
		}

		if (tower.target == null){
			tower.findTarget(monsters);
		}
	}

	/**
	 *  Checks if any of the missiles has collided with its target monster. 
	 *  Removes the missile if it has collided.
	 */
	private void updateMissiles(){
		List<Missile> toRemoveMissiles = new ArrayList<Missile>();

		for (Missile missile : missiles){
			Monster target = missile.getTarget();
			if (missile.hasCollided(target)){
				int newHP = target.getHP() - missile.getDamage();
				target.setHP(newHP);
				missile.remove = true; // This is for removing it from World
				toRemoveMissiles.add(missile);
			}
		}

		missiles.removeAll(toRemoveMissiles);
	}

	/**
	 * Updates the direction of each monster based on the in and out direction of the tile. 
	 * Removes the monsters whose hp has dropped to 0. 
	 * Also removes the missiles targeting that monster
	 */
	private void updateMonsters(){
		List<Missile> toRemoveMissiles = new ArrayList<Missile>();
		List<Monster> toRemoveMonsters = new ArrayList<Monster>();

		for (int i=0;i<monsters.size();i++){

			Monster monster = monsters.get(i);

			Block currentBlock = map.getBlockByCoordinate(monster.x, monster.y);

			if (currentBlock == null) {
				continue;
			}

			Vector direction = new Vector(0,0);

			if (currentBlock instanceof MonsterDen){
				MonsterDen md = (MonsterDen) currentBlock;
				direction = md.getDirection(monster.x, monster.y);
			}
			if (currentBlock instanceof Road){
				Road road = (Road) currentBlock;
				direction = road.getDirection(monster.x, monster.y);
			}
			if (currentBlock instanceof Castle){
				Castle castle = (Castle) currentBlock;
				direction = castle.getDirection(monster.x, monster.y);
				monster.setHP(0); // so that it vanishes
				castle.damage(1);
				
				if (castle.getHP() <= 0 && !(isGameOver)){
					gameOver();
					return;
				}
			}

			monster.setDirection(direction);

			if (monster.getHP() <= 0){

				// updates the target of each missile which was homing in on the monster.
				for(Missile missile : missiles){
					if (missile.getTarget().equals(monster)){
						// Remove the missile if this monster was the last monster
						if (i == monsters.size() - 1) {
							missile.remove = true;
							toRemoveMissiles.add(missile);
						}
						// Set the target to be the next monster
						else {
							missile.setTarget(monsters.get(i+1)); 
						}
					}
				}

				// Removes the target monster for towers that were targeting the monster
				for(Tower tower : towers){
					if (monster.equals(tower.getTarget())){
						tower.target = null;
					}
				}

				monster.remove = true; 	// This is to remove the renderable sprite from world
				toRemoveMonsters.add(monster);
			}
		}

		missiles.removeAll(toRemoveMissiles);
		monsters.removeAll(toRemoveMonsters);
	}

	/**
	 * called when game is over
	 */
	private void gameOver(){
		isGameOver = true;
		world.addEntity(new GameOverText());
	}
	
	/**
	 * set all towers' isShowRange to false
	 */
	public void setAllTowerShowRangeToFalse(){
		for (Tower tower: towers){
			tower.setShowRange(false);
		}
	}

	//********************************************************
	// 					Setter & Getters
	//********************************************************

	public void addTower(Tower tower){
		if (!tower.isBuilt){
			return;
		}
		world.addEntity(tower);
		towers.add(tower);
	}

	public void addMonster(Monster monster){
		world.addEntity(monster);
		monsters.add(monster);
	}

	public void addMissile(Missile missile){
		world.addEntity(missile);
		missiles.add(missile);
	}

	/**
	 * Adds map object to game manager and also initializes some map variables
	 * @param map
	 */
	public void addMap(Map map){
		this.map = map;
		map.addMapToWorld(world);

		try {
			monsterDen = (MonsterDen) map.getMonsterDen();
			castle = (Castle) map.getCastle();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initMap();
	}
	
	/**
	 * Initializes the monsterDen's monster creation frequency and castle's hp. 
	 */
	private void initMap(){
		monsterDen.setIsSendingMonsters(true);
		castle.setHP(CASTLE_HP);
		monsterDen.setTimeInterval(MONSTER_CREATION_INTERVAL);
	}

	public List<Tower> getTowers(){
		return towers;
	}

	public List<Monster> getMonsters(){
		return monsters;
	}

	public List<Missile> getMissiles(){
		return missiles;
	}

	public Map getMap(){
		return map;
	}

}

/**
 * We might not need this.
 * Naive implementation of displaying gameover text
 * @author Jimmy
 *
 */
class GameOverText extends Entity{

	public GameOverText() {
		super();
		
		Texture t = TextureManager.getTexture(TDspriteGame.GAME_OVER);
		Sprite s = new Sprite(t, 1024, 1024, 1);		
		x = new World().getWidth()/2;
		y = new World().getHeight()/2;
		mRenderable = s;
	}

}