package crazytd.sprites;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.util.Log;

/*
 *  Interface for sprites
 */

public class SpriteManager  {
	
	private final int BLOCK_SIZE = 64; // Size length of one block, used for testing/debugging
	
	private List<Tower> towers;
	private List<Monster> monsters;
	private List<Missile> missiles;
	
	public SpriteManager() {
		towers = new ArrayList<Tower>();
		monsters = new ArrayList<Monster>();
		missiles = new ArrayList<Missile>();	
	}
	
	
	
	/**
	 * updates List of Missiles and Monsters
	 * checks for removal
	 */
	public void updateSprites(){
		updateMissiles();
		updateMonsters();
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
				monster.remove = true;
				toRemoveMonsters.remove(monster);
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
