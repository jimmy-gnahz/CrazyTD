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

import crazytd.sprites.Tower;

import android.view.KeyEvent;

public class TDspriteGame extends Engine {
	public static final int SPRITE_TOWER = 1;
	
	@Override 
	public void init() {
		super.init();
		mWorld = new World();
		
		TextureManager.registerTexture("images/sled.png", SPRITE_TOWER);

		Tower tower = new Tower();
		float w = (float) (0.5 * (float)mWorld.getWidth());
		float h = (float) (0.5 * (float)mWorld.getHeight());
		tower.x = w;
		tower.y = h;
		mWorld.addEntity(tower);

	}
}

