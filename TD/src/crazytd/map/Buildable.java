package crazytd.map;

import org.robobrain.sdk.game.Entity;
import org.robobrain.test.TDspriteGame;

import android.util.Log;
import crazytd.sprites.Tower;

public class Buildable extends Block {
	private boolean isBuilt;
	private Tower t;
	private MapElement mBuilt;
	
	public Buildable(int x, int y) {
		super(x, y);
		isBuilt = false;
		bindTextureIndex(TDspriteGame.SPRITE_NOT_BUILT);
		bindBuiltTextureIndex(TDspriteGame.SPRITE_BUILT);
	}
	public Buildable(float x, float y) {
		super(x, y);
		isBuilt = false;
		bindTextureIndex(TDspriteGame.SPRITE_NOT_BUILT);
		bindBuiltTextureIndex(TDspriteGame.SPRITE_BUILT);
	}

	public void Build(Tower newTower){
		
		if (!isBuilt){
			Log.e("XofTower", "Towerrr "+x);
			t = newTower;
			t.x=x;
			t.y=y;
			isBuilt = true;
		}
	}
	
	public boolean getIsBuilt(){
		return isBuilt;
	}
	
	@Override
	public Entity getMapElement(){
		if(isBuilt){
			return mBuilt;
		}
		return m;
	}
	public void bindBuiltTextureIndex(int textureIndex){
		mBuilt=new MapElement(x, y, textureIndex);
	}
}
