package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.SpriteGame;

public class TDsprite extends Entity {
	public TDsprite() {
		super();
		Texture t = TextureManager.getTexture(SpriteGame.SPRITE_BAT);
		Sprite s = new Sprite(t, 64, 64, 4);
		mRenderable = s;
		mSpeed = 0.5f;
	}
	
	@Override 
	public void draw(GL10 gl) {
	    super.draw(gl);
	}
}
