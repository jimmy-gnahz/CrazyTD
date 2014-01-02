package org.robobrain.test;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.sdk.input.Multitouch;

import android.util.Log;

import crazytd.map.Block;
import crazytd.map.Buildable;
import crazytd.map.Castle;
import crazytd.map.Map;
import crazytd.map.MapParser;

import crazytd.sprites.Icon;
import crazytd.sprites.MenuBackground;
import crazytd.sprites.Missile;
import crazytd.sprites.Monster;
import crazytd.sprites.GameManager;
import crazytd.sprites.Tower;
import crazytd.sprites.UIButton;
import crazytd.sprites.TextEntity;

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
	public static final int SPRITE_HEALTH_ICON = 53;
	
	public static final int SPRITE_TOWER = 101;
	public static final int SPRITE_MONSTER = 102;
	public static final int SPRITE_MISSILE = 103;
	public static final int MONSTER_GREEN_HEALTHBAR = 104;
	public static final int MONSTER_RED_HEALTHBAR = 105;

	Map m;
	UIButton buildButton;
	MenuBackground buildBackground;
	Icon heathIcon;
	TextEntity castleHealth;
	
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
		checkBuildButton();
		if (Multitouch.getState(0) == Multitouch.POINTER_DOWN){
			chooseTowerFromMenu();
		}
		
		
		if (Multitouch.getState(0) == Multitouch.POINTER_MOVE){
			moveTowerFromMenu();
		}
		
		
		if (Multitouch.getState(0) == Multitouch.POINTER_UP) {
			if (placeTowerFromMenu()) return; // place done placing a tower (success or not) update finished
			// tower range diaplay
			if( m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0)) instanceof Buildable){
				if(((Buildable) m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))).getIsBuilt()){
					((Buildable) m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))).getTower().setShowRange(true);
					buildBackground.resetTower();
					Multitouch.clear();
					return;
				}
			}
			Multitouch.clear();

		}
	}
	
	private void chooseTowerFromMenu(){
		//when a buildable tower in the menu is picked
		buildBackground.selectTower();
		if(buildBackground.selectedTower == -1){
			buildBackground.resetTower();
		}
	}
	
	private void moveTowerFromMenu(){
		//when a buildable tower in the menu is moving
		if(buildBackground.selectedTower != -1){
			buildBackground.towers[buildBackground.selectedTower].x=Multitouch.getX(0);
			buildBackground.towers[buildBackground.selectedTower].y=Multitouch.getY(0);
		}
	}
	/**
	 * attempt to place a tower from menu, and report if it was intended to build a tower (not doing other tasks)
	 * @return  true if player tried to placed a tower,
	 * 			false if player is trying to do something else
	 */
	private boolean placeTowerFromMenu(){
		//when a buildable tower in the menu is placed
		if(buildBackground.selectedTower ==-1 ) return false;	//no tower is selected
		// place it outside the map, or not a buildable block
		if( m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))==null ||
				!(m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0)) instanceof Buildable	)){
			buildBackground.resetTower();	
			Multitouch.clear();
			return true;
		}
		// if this buildable block is occupied
		if(((Buildable) m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))).getIsBuilt()){
			buildBackground.resetTower();	
			Multitouch.clear();
			return true;
		}
		// good to go, let's build it
		else {
			((Buildable) m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))).Build(buildBackground.towers[buildBackground.selectedTower].clone());
			gameManager.addTower(((Buildable) m.getBlockByCoordinate(Multitouch.getX(0),Multitouch.getY(0))).getTower());
			
			buildBackground.resetTower();
			Multitouch.clear();
			return true;
		}
	}
	
	private void checkBuildButton(){
		if (Multitouch.getState(0) == Multitouch.POINTER_UP) {
			if(buildButton.isInside(Multitouch.getX(0), Multitouch.getY(0))){
				buildBackground.isVisible=!buildBackground.isVisible;
				Multitouch.clear();
			}
		}
	}
	
	private void loadSprites(){
		Monster monster = new Monster(Monster.FAST,30);
		monster.x = (float) (0.0 * mWorld.getWidth());
		monster.y = (float) (0.4 * mWorld.getHeight());
		monster.vx = 1.0f;
		
		Missile missile = new Missile(10,Missile.MEDIUM);
		missile.x = (float) (0.3 * mWorld.getWidth());
		missile.y = (float) (0.3 * mWorld.getHeight());
		missile.vx = 0.0f; missile.vy = 0.0f;	

		m= MapParser.parse(MapParser.testmap1);
		Tower tower = new Tower(missile,2,Tower.MEDIUM);
	
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
		
		buildButton = new UIButton(TextureManager.getTexture(SPRITE_BUILD_BUTTON),mWorld);
		Tower[] avaliableTowers = new Tower[1];
		avaliableTowers[0]= tower.clone();
		buildBackground = new MenuBackground(TextureManager.getTexture(SPRITE_BUILD_BACKGROUND),mWorld,avaliableTowers);
		mWorld.addEntity(buildBackground);
		mWorld.addEntity(buildButton);
		
		heathIcon = new Icon(TextureManager.getTexture(SPRITE_HEALTH_ICON),32,32,0.8f,0.05f,mWorld);
		castleHealth = new TextEntity(0.85*mWorld.getWidth(),0.95*mWorld.getHeight(),gameManager.CASTLE_HP+"");
		mWorld.addEntity(heathIcon);
		mWorld.addEntity(castleHealth);

		//Log.d("Width",""+mWorld.getWidth());
		//Log.d("Height",""+mWorld.getHeight());
	}
	
	private void loadTexture(){
		TextureManager.registerTexture("images/build_button.png", SPRITE_BUILD_BUTTON);
		TextureManager.registerTexture("images/BG_Stone.png", SPRITE_BUILD_BACKGROUND);
		TextureManager.registerTexture("images/heart.png", SPRITE_HEALTH_ICON);
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
		TextureManager.registerTexture("images/healthbar.png", MONSTER_GREEN_HEALTHBAR);
		TextureManager.registerTexture("images/redhealthbar.png", MONSTER_RED_HEALTHBAR);
		
	}
}

