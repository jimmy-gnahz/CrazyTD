package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.TDspriteGame;

public class Tower extends Entity{
	public Tower() {
		super();
		Texture t = TextureManager.getTexture(TDspriteGame.SPRITE_TOWER);
		Sprite s = new Sprite(t, 64, 64, 1);
		mRenderable = s;
		mSpeed = 0.5f;
	}
	
	@Override 
	public void draw(GL10 gl) {
	    super.draw(gl);
	}
}
