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
	
	public final static int BLOCK_SIZE = 64; // Size length of one block, used for testing/debugging
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
	 * updates List of Missiles and Monsters
	 * checks for removal
	 */
	public void updateSprites(){
		updateMissiles();
		updateMonsters();
		updateTowers();
	}
	
	/**
	 * updates each tower's target monster
	 * Fires missile if frequency is reached
	 */
	public void updateTowers(){
		endTime = System.currentTimeMillis();
		deltaTime = endTime - startTime;
		startTime = System.currentTimeMillis();
		
		for (Tower tower : towers) {
			updateTowerTarget(tower);
			
			// update tower.elapsedTime 
			if (tower.getElapsedTime() < tower.getFiringInterval()) {
				tower.setElapsedTime(tower.getElapsedTime() + deltaTime);
			}
			
			if ((tower.getElapsedTime() >= tower.getFiringInterval()) && (tower.target != null)){
				
				tower.setElapsedTime(0);
				Missile newMissile = tower.getMissile().clone();
				newMissile.setTarget(tower.getTarget());
				newMissile.x = tower.x; newMissile.y = tower.y;
				missiles.add(newMissile);
				world.addEntity(newMissile);
				
			}
		}
		
		
		
		// TODO
	}
	
	/**
	 * checks if target is still in range, if not then deletes the current target
	 * Sets a target if it doesn't already have one
	 */
	public void updateTowerTarget(Tower tower){

		// Deletes the current target if the target is out of range
		if (tower.target != null){
			float actualRange = tower.range * BLOCK_SIZE;
			if (tower.distToMonster(tower.target) > actualRange){
				tower.target = null;
			}
		}

		boolean foundTarget = false;
		if (tower.target == null){
			foundTarget = tower.findTarget(monsters);
		}

	}
	
	/**
	 *  Checks if any of the missiles has collided with its target monster
	 *  remove the missile if it has collided
	 */
	public void updateMissiles(){
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
	 * Removes the monsters whose hp has dropped to 0
	 * Also removes the missiles targeting that monster
	 */
	public void updateMonsters(){
		List<Missile> toRemoveMissiles = new ArrayList<Missile>();
		List<Monster> toRemoveMonsters = new ArrayList<Monster>();
		
		for (Monster monster : monsters){
			if (monster.getHP() <= 0){
				
				for(Missile missile : missiles){
					if (missile.getTarget().equals(monster)){
						missile.remove = true;
						toRemoveMissiles.add(missile);
					}
				}
				
				for(Tower tower : towers){
					if (tower.getTarget().equals(monster)){
						tower.target = null;
					}
				}
				monster.remove = true;
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
		towers.add(tower);
	}
	
	public void addMonster(Monster monster){
		monsters.add(monster);
	}
	
	public void addMissile(Missile missile){
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
