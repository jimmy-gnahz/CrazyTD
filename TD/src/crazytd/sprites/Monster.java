/*
 * Copyright (c) 2013 Jimmy Zhang
 * See the file COPYING.txt for full copyright information
 */

package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Renderable;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.sdk.graphics.Vector;
import org.robobrain.test.TDspriteGame;

import crazytd.map.Block;
import crazytd.map.Block.Direction;

public class Monster extends Entity{

	private final int HEALTH_BAR_WIDTH = 30;
	private final int HEALTH_BAR_HEIGHT = 6;
	
	/**
	 * map hp value
	 */
	int maxhp;
	
	/**
	 * Horse Power 
	 */
	int hp;
	
	/**
	 * @param speed : speed at which monsters move
	 * @param hp 	: max hp for a monster
	 */
	
	private Renderable greenHealthBar;
	private Renderable redHealthBar;
	
	public Monster(float speed, int hp) {
		super();
		Texture t = TextureManager.getTexture(TDspriteGame.SPRITE_MONSTER);
		Texture t2 = TextureManager.getTexture(TDspriteGame.MONSTER_GREEN_HEALTHBAR);
		Texture t3 = TextureManager.getTexture(TDspriteGame.MONSTER_RED_HEALTHBAR);
		Sprite s = new Sprite(t, 64, 64, 4);
		greenHealthBar = new Sprite(t2,HEALTH_BAR_WIDTH,HEALTH_BAR_HEIGHT,1);
		redHealthBar = new Sprite(t3,HEALTH_BAR_WIDTH,HEALTH_BAR_HEIGHT,1);
		mRenderable = s;
		mSpeed = speed;
		this.hp = hp;
		maxhp = hp;
	}

	public void setDirection(Vector direction){
		vx = direction.x;
		vy = direction.y;
	}
	
	public void setHP(int hp){
		this.hp = hp;
	}
	
	public int getHP(){
		return hp;
	}

	@Override 
	public void draw(GL10 gl) {
	    super.draw(gl);
	    int shiftup = 30;
	    float healthPercent = hp/(float)maxhp;
	    redHealthBar.draw(gl, x, y - shiftup ,0,1);
	    greenHealthBar.draw(gl,
	    					x - (1 - healthPercent)*HEALTH_BAR_WIDTH/2, 
	    					y - shiftup ,
	    					0,
	    					healthPercent,
	    					1);
	}
	
	@Override
	public Monster clone(){
		Monster clone = new Monster(mSpeed, hp);
		return clone;
		
	}
}
