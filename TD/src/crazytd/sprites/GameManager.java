/*
 * Copyright (c) 2013 Jimmy Zhang
 * See the file COPYING.txt for full copyright information
 */

package crazytd.sprites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Vector;

import crazytd.map.Block;
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
	
	// Size length of one block, used for testing/debugging
	public final static int BLOCK_SIZE = 64;
	
	// This is for setting MonsterDen.timeInterval
	public final static int MONSTER_CREATION_INTERVAL = 500;
	
	private long startTime;
	private long endTime;
	private long deltaTime;
	
	private List<Tower> towers;
	private List<Monster> monsters;
	private List<Missile> missiles;
	
	private Map map;
	private MonsterDen monsterDen;
	
	private World world;
	
	
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
		endTime = System.currentTimeMillis();
		deltaTime = endTime - startTime;
		startTime = System.currentTimeMillis();
		
		updateMonsterDen();
		updateMissiles();
		updateMonsters();
		updateTowers();

		
	}
	
	/**
	 * updates elapsedTime for the MonsterDen
	 */
	private void updateMonsterDen(){
		monsterDen.setElapsedTime(monsterDen.getElapsedTime() + deltaTime);
		if (monsterDen.getElapsedTime() > MONSTER_CREATION_INTERVAL){
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
			float actualRange = tower.range * BLOCK_SIZE;
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
	 * Removes the monsters whose hp has dropped to 0. 
	 * Also removes the missiles targeting that monster
	 */
	private void updateMonsters(){
		List<Missile> toRemoveMissiles = new ArrayList<Missile>();
		List<Monster> toRemoveMonsters = new ArrayList<Monster>();
		
		for (Monster monster : monsters){
			
			Block currentBlock = map.getBlockByCoordinate(monster.x, monster.y);
			Vector direction = new Vector(0,0);
			if (currentBlock instanceof MonsterDen){
				Block b = (MonsterDen) currentBlock;
				direction = b.getDirection(monster.x, monster.y);
			}
			if (currentBlock instanceof Road){
				Block b = (Road) currentBlock;
				direction = b.getDirection(monster.x, monster.y);
			}
			monster.setDirection(direction);
			
			if (monster.getHP() <= 0){
				
				// Removes the missiles that were targeting the monster
				for(Missile missile : missiles){
					if (missile.getTarget().equals(monster)){
						missile.remove = true;
						toRemoveMissiles.add(missile);
					}
				}
				
				// Removes the target monster for towers that were targeting the monster
				for(Tower tower : towers){
					if (tower.getTarget().equals(monster)){
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
	
	public void addMap(Map map){
		this.map = map;
		map.addMapToWorld(world);
		
		try {
			monsterDen = (MonsterDen) map.getMonsterDen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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