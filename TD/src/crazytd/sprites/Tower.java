package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.TDspriteGame;

public class Tower extends Entity{
	/**
	 * Firing range of the tower
	 * units in side length of grid
	 */
	float range; 
	
	/**
	 * Frequency at which towers fire missiles
	 * Measured in ___________ (TODO fill in blank)
	 */
	float firingFreq;
	
	/**
	 * cost of producing one tower (not needed for first sprint)
	 */
	int cost;
	
	/**
	 * The target monster
	 */
	Monster target;
	
	/**
	 * The missile the tower fires
	 */
	Missile missile;
	
	public Tower(Missile missile) {
		super();
		Texture t = TextureManager.getTexture(TDspriteGame.SPRITE_TOWER);
		Sprite s = new Sprite(t, 64, 64, 1);
		mRenderable = s;
	}
	
	//********************************************************
	// 					Setter & Getters
	//********************************************************
	
	public void setRange(float range) {
		this.range = range;
	}
	
	public void setFiringFreq(float frequency){
		firingFreq = frequency;
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
	
	public float getRange(){
		return range;
	}
	
	public float getFiringFreq(){
		return firingFreq;
	}
	
	public int getCost(){
		return cost;
	}
	
	public Monster getTarget(){
		return target;
	}
	
	public Missile getMissile(){
		return missile;
	}
	
	@Override 
	public void draw(GL10 gl) {
	    super.draw(gl);
	}
}
