package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;

public class MenuBackground extends Entity{
	public boolean isVisible;
	public MenuBackground(Texture t, World myWorld){
		this.x = myWorld.getWidth()*0.85f;
		this.y = myWorld.getHeight()/2;
		this.mRenderable =new Sprite(t ,(int) Math.ceil(myWorld.getWidth()*0.3), (int)(myWorld.getHeight()), 1);
		isVisible =false;
	}
	
	
	@Override
	public void draw(GL10 gl){
		if(isVisible){
			super.draw(gl);
		}
	}
}
