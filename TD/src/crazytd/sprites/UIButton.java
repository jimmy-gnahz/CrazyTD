package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.test.CrazyTowerGame;



public class UIButton extends Entity{
	
	//protected boolean isTextureLoaded = false;	
	protected int diameter;
	
	public UIButton(Texture t, World myWorld){
		this.x = myWorld.getWidth()*0.85f;
		this.y = myWorld.getHeight()*0.85f;
		diameter = Math.round(myWorld.getWidth()*0.1f);
		this.mRenderable = new Sprite(t , diameter, diameter, 1);
	}
	
	public boolean isInside(float x, float y){
		return diameter > Math.sqrt(
				Math.pow((this.x-x),2) + Math.pow((this.y - y),2));
	}
}
