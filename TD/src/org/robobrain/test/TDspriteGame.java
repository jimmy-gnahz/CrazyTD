package org.robobrain.test;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.TextureManager;

import crazytd.sprites.Missile;
import crazytd.sprites.Monster;
import crazytd.sprites.SpriteManager;
import crazytd.sprites.Tower;

public class TDspriteGame extends Engine {
	public static final int SPRITE_TOWER = 1;
	public static final int SPRITE_MONSTER = 2;
	public static final int SPRITE_MISSILE = 3;
	
	SpriteManager spriteManager;
	@Override 
	public void init() {
		super.init();
		mWorld = new World();
		
		TextureManager.registerTexture("images/sled.png", SPRITE_TOWER);
		TextureManager.registerTexture("images/bat.png", SPRITE_MONSTER);
		TextureManager.registerTexture("images/missile.jpg", SPRITE_MISSILE);
		
		Monster monster = new Monster(0.03f,100);
		monster.x = (float) (0.3 * mWorld.getWidth());
		monster.y = (float) (0.8 * mWorld.getHeight());
		monster.vx = 1.0f;
		
		Missile missile = new Missile(monster,20,0.1f);
		missile.x = (float) (0.3 * mWorld.getWidth());
		missile.y = (float) (0.3 * mWorld.getHeight());
		missile.vx = 0.0f; missile.vy = 0.1f;
		
		Tower tower = new Tower(missile);
		tower.x = (float) (0.5 * (float)mWorld.getWidth());
		tower.y = (float) (0.5 * (float)mWorld.getHeight());
		
		spriteManager = new SpriteManager();
		spriteManager.addMissile(missile);
		spriteManager.addMonster(monster);
		spriteManager.addTower(tower);
		
		mWorld.addSpriteManager(spriteManager);
		mWorld.addEntity(missile);
		mWorld.addEntity(tower);
		mWorld.addEntity(monster);

	}
}

