package crazytd.sprites;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/*
 *  Interface for sprites
 */

public class SpriteManager  {
	
	private List<Tower> towers;
	private List<Monster> monsters;
	private List<Missile> missiles;
	
	public SpriteManager() {
		towers = new ArrayList<Tower>();
		monsters = new ArrayList<Monster>();
		missiles = new ArrayList<Missile>();	
	}
	
	/**
	 *  Checks if any of the missiles has collided with its target monster
	 *  remove the missile if it has collided
	 */
	public void updateMissiles(){
		for (Missile missile : missiles){
			Monster target = missile.getTarget();
			if (missile.hasCollided(target)){
				
				Log.e("Collision","happened "+target.getHP());
				int newHP = target.getHP() - missile.getDamage();
				target.setHP(newHP);
				Log.e("collision","finished " +target.getHP());
				missile.remove = true; // This is for removing it from World
				missiles.remove(missile);
			}
		}
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
