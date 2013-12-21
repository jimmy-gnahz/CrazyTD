package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.TDspriteGame;

public class Missile extends Entity{

	/**
	 * Attack damage of missile
	 */
	int damage;
	
	/**
	 * The target monster of the missile
	 */
	Monster target;
	
	public Missile(Monster target, int damage, float speed) {
		super();
		Texture t = TextureManager.getTexture(TDspriteGame.SPRITE_MISSILE);
		Sprite s = new Sprite(t, 20, 20, 1);
		mRenderable = s;
		mSpeed = speed;
		this.damage = damage;
		this.target = target;
		
	}
	
	public void setTarget(Monster m){
		target = m;
	}
	
	public void setDamage(int d){
		damage = d;
	}
	
	public Monster getTarget(){
		return target;
	}
	
	public int getDamage(){
		return damage;
	}
	
	@Override 
	public void draw(GL10 gl) {
	    super.draw(gl);
	}
}