package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;

import crazytd.map.Block;

public class MenuBackground extends Entity{
	
	public boolean isVisible;
	
	/**
	 * the 1st tower that can be built
	 */
	public Tower[] towers;
	/**
	 * the coordinate of the 1st tower (that appear in the menu)
	 */
	private float xOfTowers[],yOfTowers[];	
	
	/**
	 * a integer (index) to show which Tower is selected and about to be built, default to be -1
	 */
	public int selectedTower = -1;
	
	public MenuBackground(Texture t, World myWorld, Tower[] towersToBeBuilt){
		this.x = myWorld.getWidth()*0.85f;
		this.y = myWorld.getHeight()*0.9f/2;
		this.mRenderable = new Sprite(t ,(int) Math.ceil(myWorld.getWidth()*0.3), (int)(myWorld.getHeight()*0.9f), 1);
		isVisible = false;
		
		towers=towersToBeBuilt;
		xOfTowers = new float[towersToBeBuilt.length];
		yOfTowers = new float[towersToBeBuilt.length];
		for(int i = 0; i< towersToBeBuilt.length; i++){
			xOfTowers[i] = myWorld.getWidth()*0.85f;
			yOfTowers[i] = myWorld.getHeight()*(i+2)*0.1f;
			towers[i].setX(xOfTowers[i]);
			towers[i].setY(yOfTowers[i]);
		}

		//myWorld.addEntity(tower1);
	}
	
	public boolean isPointerAt(float x, float y, int towerIndex){
		if(!isVisible) return false;
		if(x < (xOfTowers[towerIndex]+Block.tileSize/2) &&
		   x > (xOfTowers[towerIndex]-Block.tileSize/2) &&
		   y < (yOfTowers[towerIndex]+Block.tileSize/2) &&
		   y > (yOfTowers[towerIndex]-Block.tileSize/2)){
			return true;
		}
		return false;
	}
	
	/**
	 * reset the selectedTower's stats include the tower's:
	 * 			x, y, isBuilt isShowRange
	 * and the selectedTower is reset to -1
	 */
	public void resetTower(){
		if(selectedTower==-1) return;
		towers[selectedTower].x=xOfTowers[selectedTower];
		towers[selectedTower].y=yOfTowers[selectedTower];
		towers[selectedTower].isBuilt = false;
		towers[selectedTower].isShowRange = false;
		selectedTower = -1;
	}
	
	@Override
	public void draw(GL10 gl){
		if(isVisible){
			super.draw(gl);
			if (towers.length<=0) return;
			for(int i = 0; i < towers.length;i++)
				towers[i].draw(gl);
		}
	}
}
