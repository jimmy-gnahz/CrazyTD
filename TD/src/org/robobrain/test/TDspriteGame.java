package org.robobrain.test;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.TextureManager;

import android.util.Log;

import crazytd.map.Block;
import crazytd.map.Buildable;
import crazytd.map.Castle;
import crazytd.map.Map;
import crazytd.map.MapParser;
import crazytd.map.MonsterDen;
import crazytd.map.Road;
import crazytd.map.WasteLand;
import crazytd.sprites.Missile;
import crazytd.sprites.Monster;
import crazytd.sprites.GameManager;
import crazytd.sprites.Tower;

public class TDspriteGame extends Engine {
	public static final int SPRITE_ROAD =1;
	public static final int SPRITE_WASTELAND = 2;
	public static final int SPRITE_NOT_BUILT = 3;
	public static final int SPRITE_BUILT=4;
	public static final int SPRITE_MONSTER_DEN=5;
	public static final int SPRITE_CASTLE=6;
	
	public static final int SPRITE_TOWER = 101;
	public static final int SPRITE_MONSTER = 102;
	public static final int SPRITE_MISSILE = 103;
	public static final int MONSTER_GREEN_HEALTHBAR = 104;
	public static final int MONSTER_RED_HEALTHBAR = 105;
	
	GameManager gameManager;
	@Override 
	public void init() {
		super.init();
		mWorld = new World();
		
		loadTexture();

		loadSprites();
	
	}
	
	private void loadSprites(){
		Monster monster = new Monster( Monster.FAST , 100);
		monster.x = (float) (0.0 * mWorld.getWidth());
		monster.y = (float) (0.4 * mWorld.getHeight());
		monster.vx = 1.0f;
		
		Missile missile = new Missile(monster , 20 , Missile.FAST);
		missile.x = (float) (0.3 * mWorld.getWidth());
		missile.y = (float) (0.3 * mWorld.getHeight());
		missile.vx = 0.0f; missile.vy = 0.0f;	

		Tower tower = new Tower(missile,1,Tower.MEDIUM);

		Map m = MapParser.parse(MapParser.testmap1);
	
		Block b= m.getBlock(3, 2);
		if(b instanceof Buildable){
			if(!((Buildable) b).getIsBuilt()){
				((Buildable) b).Build(tower);
			}
		}		
		
		m.setMonster(monster);
		
		gameManager = new GameManager(mWorld);
		gameManager.addMap(m);
		gameManager.addTower(tower);
		mWorld.addGameManager(gameManager);
	}
	
	private void loadTexture(){
		TextureManager.registerTexture("images/sled.png", SPRITE_TOWER);
		TextureManager.registerTexture("images/bat.png", SPRITE_MONSTER);
		TextureManager.registerTexture("images/missile.jpg", SPRITE_MISSILE);
		TextureManager.registerTexture("images/road.png", SPRITE_ROAD);
		TextureManager.registerTexture("images/built.png", SPRITE_BUILT);
		TextureManager.registerTexture("images/not_built.png", SPRITE_NOT_BUILT);
		TextureManager.registerTexture("images/wasteland.png", SPRITE_WASTELAND);
		TextureManager.registerTexture("images/monster_den.png", SPRITE_MONSTER_DEN);
		TextureManager.registerTexture("images/castle.png", SPRITE_CASTLE);
		TextureManager.registerTexture("images/healthbar.png", MONSTER_GREEN_HEALTHBAR);
		TextureManager.registerTexture("images/redhealthbar.png", MONSTER_RED_HEALTHBAR);
		
	}
}

