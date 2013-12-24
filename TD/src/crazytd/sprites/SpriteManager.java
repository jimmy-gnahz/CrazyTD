/*
 * Copyright (c) 2013 Jimmy Zhang
 * See the file COPYING.txt for full copyright information
 */

package crazytd.sprites;

import java.util.ArrayList;
import java.util.List;

import org.robobrain.sdk.game.World;

import android.util.Log;

/*
 *  Interface for sprites
 */

public class SpriteManager  {
	
	// Size length of one block, used for testing/debugging
	public final static int BLOCK_SIZE = 64;
	
	private long startTime;
	private long endTime;
	private long deltaTime;
	
	private List<Tower> towers;
	private List<Monster> monsters;
	private List<Missile> missiles;
	
	private World world;
	
	public SpriteManager(World world) {
		this.world = world;
		towers = new ArrayList<Tower>();
		monsters = new ArrayList<Monster>();
		missiles = new ArrayList<Missile>();
		startTime = System.currentTimeMillis();
	}
	
	
	/**
	 * Updates the lists of sprites
	 */
	public void updateSprites(){
		updateMissiles();
		updateMonsters();
		updateTowers();
	}
	
	/**
	 * Updates elapsedTime for each tower. 
	 * Updates each tower's target monster. 
	 * Fires missile if frequency is reached
	 */
	private void updateTowers(){
		endTime = System.currentTimeMillis();
		deltaTime = endTime - startTime;
		startTime = System.currentTimeMillis();
		
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
				
				monster.remove = true; 	// To remove the texture
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
	
	public List<Tower> getTowers(){
		return towers;
	}
	
	public List<Monster> getMonsters(){
		return monsters;
	}
	
	public List<Missile> getMissiles(){
		return missiles;
	}
	
}
