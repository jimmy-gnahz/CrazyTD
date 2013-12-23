/*
 * Copyright (c) 2013 Jimmy Zhang
 * See the file COPYING.txt for full copyright information
 */

package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.TDspriteGame;

public class Monster extends Entity{

	/**
	 * Horse Power 
	 */
	int hp;
	
	/**
	 * @param speed : speed at which monsters move
	 * @param hp 	: max hp for a monster
	 */
	public Monster(float speed, int hp) {
		super();
		Texture t = TextureManager.getTexture(TDspriteGame.SPRITE_MONSTER);
		Sprite s = new Sprite(t, 64, 64, 4);
		mRenderable = s;
		mSpeed = speed;
		this.hp = hp;
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
	}
}
