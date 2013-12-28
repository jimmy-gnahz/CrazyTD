package org.robobrain.test;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.sdk.input.Multitouch;

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
import crazytd.sprites.SpriteManager;
import crazytd.sprites.Tower;

public class CrazyTowerGame extends Engine {
	public static final int SPRITE_ROAD =1;
	public static final int SPRITE_WASTELAND = 2;
	public static final int SPRITE_NOT_BUILT = 3;
	public static final int SPRITE_BUILT=4;
	public static final int SPRITE_MONSTER_DEN=5;
	public static final int SPRITE_CASTLE=6;
	public static final int SPRITE_CIRCLE=7;
	
	public static final int SPRITE_TOWER = 101;
	public static final int SPRITE_MONSTER = 102;
	public static final int SPRITE_MISSILE = 103;

	Monster monster;

	Map m;
	Tower tower;
	
	
	
	
	SpriteManager spriteManager;
	@Override 
	public void init() {
		super.init();
		mWorld = new World();
		
		loadTexture();

		loadSprites();
	
	}
	
	@Override
	public void update(long time){
		super.update(time);
		if (Multitouch.getState(0) == Multitouch.POINTER_DOWN) {
			Block b = m.getBlockByCoordinate(Multitouch.getX(0), Multitouch.getX(0));
			if(b.getClass()==Buildable.class){
				if(((Buildable)b).getIsBuilt()){

				}
			}
		}
	}
	
	private void loadSprites(){
		monster = new Monster(0.1f,30);
		monster.x = (float) (0.0 * mWorld.getWidth());
		monster.y = (float) (0.4 * mWorld.getHeight());
		monster.vx = 1.0f;
		
		Monster monster2 = new Monster(0.05f,300);
		monster2.x = (float) (0.0 * mWorld.getWidth());
		monster2.y = (float) (0.6 * mWorld.getHeight());
		monster2.vx = 1.0f;
		
		Missile missile = new Missile(monster,10,0.1f);
		missile.x = (float) (0.3 * mWorld.getWidth());
		missile.y = (float) (0.3 * mWorld.getHeight());
		missile.vx = 0.0f; missile.vy = 0.0f;	

		Map m= MapParser.parse(MapParser.testmap1);
		Tower tower = new Tower(missile,5,Tower.QUICK_FIRE);
	
		Block b= m.getBlock(2, 3);
		if(b instanceof Buildable){
			if(!((Buildable) b).getIsBuilt()){
				((Buildable) b).Build(tower);
			}
		}		
		
		m.addMapToWorld(mWorld);
		
		spriteManager = new SpriteManager(mWorld);
		spriteManager.addMissile(missile);
		spriteManager.addMonster(monster);
		spriteManager.addMonster(monster2);
		spriteManager.addTower(tower);
		mWorld.addSpriteManager(spriteManager);
		Log.d("Width",""+mWorld.getWidth());
		Log.d("Height",""+mWorld.getHeight());
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
		TextureManager.registerTexture("images/circle.png", SPRITE_CIRCLE);
		
	}
}

