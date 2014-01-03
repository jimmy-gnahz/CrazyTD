package crazytd.sprites;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.TextSprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.FontGame;
import org.robobrain.test.TDspriteGame;

public class TextEntity extends Entity{
	public TextEntity(double x, double y,String s){
		Texture tex = TextureManager.getTexture(FontGame.FONT_TEXTURE);
		TextSprite ts = new TextSprite(tex);
		ts.x=(float)x;
		ts.y=(float)y;
		ts.setMessage(s);
		mRenderable = ts;
	}
	
	@Override 
	public void update(long time) {
		//super.update(time);
	}
	
	public void updateText(String s){
		((TextSprite)mRenderable).setMessage(s);
	}
}
