package crazytd.map;

import android.util.Log;
import crazytd.map.Block.BlockType;
import crazytd.sprites.Tower;

public class Buildable extends Block {
	private boolean isBuilt;
	private Tower t;
	
	public Buildable(int x, int y) {
		super(x, y);
		isBuilt = false;
	}
	public Buildable(float x, float y) {
		super(x, y);
		isBuilt = false;
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
	
}
