package crazytd.map;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.test.TDspriteGame;

import android.util.Log;
import crazytd.sprites.Tower;

public class Buildable extends Block {
	private boolean isBuilt;
	private Tower t;
	
	
	public Buildable(int x, int y) {
		super(x, y);
		isBuilt = false;
		bindTextureIndex(TDspriteGame.SPRITE_NOT_BUILT);
		//bindBuiltTextureIndex(TDspriteGame.SPRITE_BUILT);
	}
	public Buildable(float x, float y) {
		super(x, y);
		isBuilt = false;
		bindTextureIndex(TDspriteGame.SPRITE_NOT_BUILT);
		//bindBuiltTextureIndex(TDspriteGame.SPRITE_BUILT);
	}

	public void Build(Tower newTower){
		
		if (!isBuilt){
			t = newTower;
			t.x = x;
			t.y = y;
			t.buildTower();
			isBuilt = true;
			bindTextureIndex(TDspriteGame.SPRITE_BUILT);
		}
	}
	/**
	 * sell a tower on the block
	 * @return the money to be refund, or 0 if there is no tower
	 */
	public void Destroy(){
		if( t == null )return;
		t = null;
		isBuilt = false;
	}
	
	public boolean getIsBuilt(){
		return isBuilt;
	} 
	
	/**
	 * if the tower is not built, throws an exception, please check before calling this
	 * @return
	 */
	public Tower getTower(){
		//if(isBuilt){
			return t;
		//}
	}
}
