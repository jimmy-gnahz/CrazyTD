/*
 * Copyright (c) 2013 Jimmy Zhang
 * See the file COPYING.txt for full copyright information
 */

package crazytd.sprites;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Renderable;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.CrazyTowerGame;
import org.robobrain.test.TDspriteGame;

import crazytd.map.Block;

public class Tower extends Entity{
	
	/**
	 * indicates whether or not the tower is built
	 */
	protected boolean isBuilt = false;
	
	/**
	 * Firing range of the tower. 
	 * Unit for measurements is edge length of one tile
	 */
	protected float range; 
	
	/**
	 * Firing frequencies. 
	 * Measured in #fires/minute
	 */
	public static final float FAST = 120 , MEDIUM = 60 , SLOW = 30;

	/**
	 * Frequency at which towers fire missiles. 
	 * One of {FAST,MEDIUM,SLOW}
	 */
	protected float firingFreq;
	
	/**
	 * The elapsed time from the last time the tower fired. 
	 * Measured in Milliseconds
	 */
	protected float elapsedTime;
	
	/**
	 * cost of producing one tower (perhaps not needed for first sprint)
	 */
	protected int cost =15;
	
	/**
	 * refund if this tower is sold;
	 */
	protected int refund = 1;
	/**
	 * The target monster
	 */
	protected Monster target;
	
	/**
	 * The type of missile the tower fires
	 */
	protected Missile missile;
	
	/**
	 * The game selected this tower to display its range.
	 */
	public boolean isShowRange;
	
	private Renderable rangeCircle;

	private Renderable sellIcon;


	/**
	 * @param missile
	 * @param range measured in number of tiles, 
	 * @param frequency firing speed, recommended: FAST, MEDIUM, SLOW
	 */
	public Tower(Missile missile, float range, float frequency) {
		super();
		Texture t = TextureManager.getTexture(TDspriteGame.SPRITE_TOWER);
		Texture tc = TextureManager.getTexture(CrazyTowerGame.SPRITE_CIRCLE);
		Texture ts = TextureManager.getTexture(CrazyTowerGame.SPRITE_SELLICON);
		Sprite s = new Sprite(t, 64, 64, 1);
		rangeCircle = new Sprite(tc,256,256, 1);
		sellIcon = new Sprite(ts,64,64, 1);
		mRenderable = s;
		this.range = range;
		this.firingFreq = frequency;
		this.missile = missile;
		this.isShowRange = false;
	}
	
	@Override 
	public void draw(GL10 gl) {
	    super.draw(gl);
	    if(isShowRange){
	    	rangeCircle.draw(gl, super.x, super.y, 0, range*2*Block.tileSize/256);
	    	sellIcon.draw(gl, super.x+Block.tileSize/2, super.y-Block.tileSize/2, 0, Block.tileSize/64, Block.tileSize/64);
	    }
	}

	/**
	 * Sets the target and returns boolean indicating whether we have found it or not
	 * @param monsters the list of monsters in game
	 * @return true if we have found a target, false otherwise (a tower is not built may not find any target)
	 */
	public boolean findTarget(List<Monster> monsters) {
		if (!isBuilt){
			return false;
		}
			
		float actualRange = range * GameManager.TILE_SIZE;
		
		// Set this.target as the first monster in firing range
		for(Monster monster: monsters){
			float dist = distToMonster(monster);
			if (dist < actualRange) {
				target = monster;
				return true;
			}
		}
		
		// all monsters are not in range
		return false;
	}
	
	public float distToMonster(Monster monster){
		return (float) Math.sqrt(
				Math.pow((monster.x - x),2) + Math.pow((monster.y - y),2));
	}
	
	public Tower clone(){
		return new Tower(missile, range, firingFreq);
	}
	
	//********************************************************
	// 					Setter & Getters
	//********************************************************
	
	public void setRange(float range) {
		this.range = range;
	}
	
	public void setShowRange(boolean isShowing){
		this.isShowRange = isShowing;
	}
	public void setFiringFreq(float frequency){
		firingFreq = frequency;
		
		// Tower will fire when elapsedTime >= firingFreq
		// We set this to max so that it will fire the first missile when a monster is just within range
		elapsedTime = frequency; 
	}
	
	public void setCost(int cost){
		this.cost = cost;
	}
	
	public void setTarget(Monster monster){
		target = monster;
	}
	
	public void setMissile(Missile missile){
		this.missile = missile;
	}
	
	public void setElapsedTime(float et){
		elapsedTime = et;
	}
	
	public float getRange(){
		return range;
	}
	
	public float getFiringFreq(){
		return firingFreq;
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getRefund(){
		return refund;
	}
	
	public Monster getTarget(){
		return target;
	}
	
	public Missile getMissile(){
		return missile;
	}
	
	public float getElapsedTime(){
		return elapsedTime;
	}
	
	/**
	 * @return the time between firing each missile in milliseconds
	 */
	public float getFiringInterval(){
		float intervalInSeconds = 60/firingFreq;
		return intervalInSeconds * 1000;
	}
	
	/**
	 * build the tower, should only be called by Buildable.build(...);
	 */
	public void buildTower(){
		isBuilt = true;
	}
}
