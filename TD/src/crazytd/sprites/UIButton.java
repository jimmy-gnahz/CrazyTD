package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Renderable;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.CrazyTowerGame;



public class UIButton extends Entity{
	
	//protected boolean isTextureLoaded = false;	
	private final int DIAMETER = 100;
	
	public UIButton(Texture t, float x, float y){
		this.x = x;
		this.y = y;
		this.mRenderable = new Sprite(t , DIAMETER, DIAMETER, 1);
	}
	
	public boolean isInside(float x, float y){
		return DIAMETER > Math.sqrt(
				Math.pow((this.x-x),2) + Math.pow((this.y - y),2));
	}
}
