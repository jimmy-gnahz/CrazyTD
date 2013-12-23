package org.robobrain.test;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.TextureManager;

import android.util.Log;

import crazytd.map.Block;
import crazytd.map.Buildable;
import crazytd.map.Road;
import crazytd.map.WasteLand;
import crazytd.sprites.Missile;
import crazytd.sprites.Monster;
import crazytd.sprites.SpriteManager;
import crazytd.sprites.Tower;

public class TDspriteGame extends Engine {
	public static final int SPRITE_ROAD =1;
	public static final int SPRITE_WASTELAND = 2;
	public static final int SPRITE_NOT_BUILT = 3;
	public static final int SPRITE_BUILT=4;
	
	public static final int SPRITE_TOWER = 101;
	public static final int SPRITE_MONSTER = 102;
	public static final int SPRITE_MISSILE = 103;
	
	SpriteManager spriteManager;
	@Override 
	public void init() {
		super.init();
		mWorld = new World();
		
		TextureManager.registerTexture("images/sled.png", SPRITE_TOWER);
		TextureManager.registerTexture("images/bat.png", SPRITE_MONSTER);
		TextureManager.registerTexture("images/missile.jpg", SPRITE_MISSILE);
		TextureManager.registerTexture("images/road.png", SPRITE_ROAD);
		TextureManager.registerTexture("images/built.png", SPRITE_BUILT);
		TextureManager.registerTexture("images/not_built.png", SPRITE_NOT_BUILT);
		TextureManager.registerTexture("images/wasteland.png", SPRITE_WASTELAND);
		
		Monster monster = new Monster(0.03f,30);
		monster.x = (float) (0.3 * mWorld.getWidth());
		monster.y = (float) (0.8 * mWorld.getHeight());
		monster.vx = 1.0f;
		
		Missile missile = new Missile(monster,50,0.1f);
		missile.x = (float) (0.3 * mWorld.getWidth());
		missile.y = (float) (0.3 * mWorld.getHeight());
		missile.vx = 0.0f; missile.vy = 0.1f;
		
		WasteLand w = new WasteLand(2,2);
		w.bindTextureIndex(SPRITE_WASTELAND);
		
		Road r= new Road(0,0,"EAST","WEST");
		r.bindTextureIndex(SPRITE_ROAD);
		
		Buildable notBuilt= new Buildable(3,4);
		notBuilt.bindTextureIndex(SPRITE_NOT_BUILT);
		notBuilt.bindBuiltTextureIndex(SPRITE_BUILT);
		
		Block b = new Buildable((float)((float)mWorld.getWidth()*0.5),(float)((float)mWorld.getHeight()*0.5));
		b.bindTextureIndex(SPRITE_NOT_BUILT);
		((Buildable)b).bindBuiltTextureIndex(SPRITE_BUILT);
		Missile missile2 = new Missile(monster,20,0.03f);
		missile2.x = 0;
		missile2.y = 0;
		missile2.vx = 1; missile2.vy = 1;
		
		Tower tower = new Tower(missile);
		if(b.getClass()==Buildable.class){
			if(!((Buildable) b).getIsBuilt()){
				((Buildable) b).Build(tower);
			}
		}
			
		//tower.x = (float) (0.5 * (float)mWorld.getWidth());
		Log.e("XofTower", ""+tower.x);
//		tower.y = (float) (0.5 * (float)mWorld.getHeight());
		
		spriteManager = new SpriteManager();
		spriteManager.addMissile(missile);
		spriteManager.addMonster(monster);
		spriteManager.addTower(tower);
		spriteManager.addMissile(missile2);

		
		mWorld.addSpriteManager(spriteManager);
		mWorld.addEntity(w.getMapElement());
		mWorld.addEntity(r.getMapElement());
		mWorld.addEntity(b.getMapElement());
		mWorld.addEntity(notBuilt.getMapElement());
		mWorld.addEntity(missile);
		mWorld.addEntity(missile2);
		mWorld.addEntity(tower);
		mWorld.addEntity(monster);

		
	}
}

