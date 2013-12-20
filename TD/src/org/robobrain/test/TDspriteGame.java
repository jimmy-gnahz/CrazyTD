package org.robobrain.test;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.SimpleSprite;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.sdk.input.Keyboard;
import org.robobrain.sdk.input.Multitouch;

import crazytd.sprites.Missile;
import crazytd.sprites.Monster;
import crazytd.sprites.Tower;

import android.view.KeyEvent;

public class TDspriteGame extends Engine {
	public static final int SPRITE_TOWER = 1;
	public static final int SPRITE_MONSTER = 2;
	public static final int SPRITE_MISSILE = 3;
	
	@Override 
	public void init() {
		super.init();
		mWorld = new World();
		
		TextureManager.registerTexture("images/sled.png", SPRITE_TOWER);
		TextureManager.registerTexture("images/bat.png", SPRITE_MONSTER);
		TextureManager.registerTexture("images/missile.jpg", SPRITE_MISSILE);
		
		Missile missile = new Missile();
		missile.x = (float) (0.3 * mWorld.getWidth());
		missile.y = (float) (0.3 * mWorld.getHeight());
		
		Monster monster = new Monster();
		monster.x = (float) (0.3 * mWorld.getWidth());
		monster.y = (float) (0.5 * mWorld.getHeight());

		Tower tower = new Tower();
		tower.x = (float) (0.5 * (float)mWorld.getWidth());
		tower.y = (float) (0.5 * (float)mWorld.getHeight());
		
		mWorld.addEntity(missile);
		mWorld.addEntity(tower);
		mWorld.addEntity(monster);

	}
}

