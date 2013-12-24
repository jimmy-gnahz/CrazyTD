package org.robobrain.test;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.TextureManager;

import android.util.Log;

import crazytd.map.Block;
import crazytd.map.Buildable;
import crazytd.map.Castle;
import crazytd.map.Map;
import crazytd.map.MonsterDen;
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
	public static final int SPRITE_MONSTER_DEN=5;
	public static final int SPRITE_CASTLE=6;
	
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
		TextureManager.registerTexture("images/monster_den.png", SPRITE_MONSTER_DEN);
		TextureManager.registerTexture("images/castle.png", SPRITE_CASTLE);
		

		Monster monster = new Monster(0.1f,30);
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
		


//		WasteLand w = new WasteLand(0,2);
//		MonsterDen d= new MonsterDen(0,0,MonsterDen.Direction.EAST);
//		Road r1= new Road(1,0,Road.Direction.WEST,Road.Direction.EAST);
//		Road r2 = new Road(2,0,Road.Direction.WEST,Road.Direction.EAST);
//		Castle c = new Castle(3,0,50);
//		Buildable notBuilt= new Buildable(1,1);		
//		Block b = new Buildable((float)((float)mWorld.getWidth()*0.5),(float)((float)mWorld.getHeight()*0.5));
//
//		WasteLand w = new WasteLand(0,2);
//		MonsterDen d = new MonsterDen(0,0,MonsterDen.Direction.EAST);
//		Road r1= new Road(1,0,Road.Direction.WEST,Road.Direction.EAST);
//		Road r2 = new Road(2,0,Road.Direction.WEST,Road.Direction.EAST);
//		Castle c = new Castle(3,0,50);
//		Buildable notBuilt = new Buildable(1,1);		
//		Block b = new Buildable((float)(mWorld.getWidth()*0.5),(float)(mWorld.getHeight()*0.5));

		
		Map m= new Map(4,4);
		Tower tower = new Tower(missile,2,Tower.FAST);
		
		Block b= m.getBlock(3, 2);
		if(b.getClass()==Buildable.class){
			if(!((Buildable) b).getIsBuilt()){
				((Buildable) b).Build(tower);
			}
		}

		Block[][] ma =m.getMap();
		for(int x=0;x<ma.length;x++){
			for(int y=0;y<ma[x].length;y++){
				mWorld.addEntity(ma[x][y].getMapElement());
			}
		}
		


//		mWorld.addEntity(w.getMapElement());
//		mWorld.addEntity(r1.getMapElement());
//		mWorld.addEntity(r2.getMapElement());
//		mWorld.addEntity(c.getMapElement());
//		mWorld.addEntity(d.getMapElement());
//		mWorld.addEntity(b.getMapElement());
//		mWorld.addEntity(notBuilt.getMapElement());
		
		spriteManager = new SpriteManager(mWorld);
		spriteManager.addMissile(missile);
		spriteManager.addMonster(monster);
		spriteManager.addMonster(monster2);
		spriteManager.addTower(tower);
		mWorld.addSpriteManager(spriteManager);
	
	}
}

