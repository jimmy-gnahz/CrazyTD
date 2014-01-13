package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Color;
import org.robobrain.sdk.graphics.TextSprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.test.CrazyTowerGame;
import org.robobrain.test.FontGame;
import org.robobrain.test.TDspriteGame;

public class TextEntity extends Entity{
	private float scale=20;
	public TextEntity(double x, double y,String s){
		Texture tex = TextureManager.getTexture(CrazyTowerGame.FONT_TEXTURE);
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
	
	public void setFontScale(float scale){
		this.scale = scale;
	}
	public void updateText(String s){
		((TextSprite)mRenderable).setMessage(s);
	}
	
	@Override 
	public void draw(GL10 gl) {
		((TextSprite)mRenderable).draw(gl, scale);
	}
	
	public void draw(GL10 gl, float scale){
		this.scale=scale;
		((TextSprite)mRenderable).draw(gl, scale);
	}
	public void setColor(Color c){
		((TextSprite)mRenderable).setColor(c);
	}
}
