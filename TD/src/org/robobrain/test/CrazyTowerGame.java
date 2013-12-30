package org.robobrain.test;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.sdk.graphics.Vector;
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
import crazytd.sprites.MenuBackground;
import crazytd.sprites.Missile;
import crazytd.sprites.Monster;
import crazytd.sprites.GameManager;
import crazytd.sprites.Tower;
import crazytd.sprites.UIButton;

public class CrazyTowerGame extends Engine {
	public static final int SPRITE_ROAD =1;
	public static final int SPRITE_WASTELAND = 2;
	public static final int SPRITE_NOT_BUILT = 3;
	public static final int SPRITE_BUILT=4;
	public static final int SPRITE_MONSTER_DEN=5;
	public static final int SPRITE_CASTLE=6;
	public static final int SPRITE_CIRCLE=7;
	
	public static final int SPRITE_BUILD_BUTTON = 51;
	public static final int SPRITE_BUILD_BACKGROUND = 52;
	
	public static final int SPRITE_TOWER = 101;
	public static final int SPRITE_MONSTER = 102;
	public static final int SPRITE_MISSILE = 103;

	Map m;
	UIButton buildButton;
	MenuBackground buildBackground;
	
	GameManager gameManager;
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
		if (Multitouch.getState(0) == Multitouch.POINTER_UP) {
			if(buildButton.isInside(Multitouch.getX(0), Multitouch.getY(0))){
				buildBackground.isVisible=!buildBackground.isVisible;
				Multitouch.clear();
				return;
			}
				
			if( m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))==null) return;
			if( m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0)).getClass()==Buildable.class){
				if(((Buildable) m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))).getIsBuilt()){
					((Buildable) m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))).getTower().setShowRange(true);
					Multitouch.clear();
					return;
				}
			}
			if(gameManager.getTowers().size()<=0)return;
			for(int i =0; i<gameManager.getTowers().size();i++){
				gameManager.getTowers().get(i).setShowRange(false);
			}
		}
	}
	
	private void loadSprites(){
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

		m= MapParser.parse(MapParser.testmap1);
		Tower tower = new Tower(missile,5,Tower.QUICK_FIRE);
	
		Block b= m.getBlock(3, 2);
		if(b instanceof Buildable){
			if(!((Buildable) b).getIsBuilt()){
				((Buildable) b).Build(tower);
			}
		}		
		
		m.setMonster(monster);
		
		gameManager = new GameManager(mWorld);
		gameManager.addMap(m);
		gameManager.addMissile(missile);
//		gameManager.addMonster(monster);
//		gameManager.addMonster(monster2);
		gameManager.addTower(tower);
		mWorld.addGameManager(gameManager);
		
		buildButton = new UIButton(TextureManager.getTexture(SPRITE_BUILD_BUTTON),mWorld.getWidth()*0.85f,mWorld.getHeight()*0.85f);
		buildBackground = new MenuBackground(TextureManager.getTexture(SPRITE_BUILD_BACKGROUND),mWorld);
		mWorld.addEntity(buildBackground);
		mWorld.addEntity(buildButton);

		Log.d("Width",""+mWorld.getWidth());
		Log.d("Height",""+mWorld.getHeight());
	}
	
	private void loadTexture(){
		TextureManager.registerTexture("images/build_button.png", SPRITE_BUILD_BUTTON);
		TextureManager.registerTexture("images/BG_Stone.png", SPRITE_BUILD_BACKGROUND);
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

