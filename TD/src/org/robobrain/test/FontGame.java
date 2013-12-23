package org.robobrain.test;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Engine;
import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.TextSprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import crazytd.map.*;
public class FontGame extends Engine {
	public static final int FONT_TEXTURE = 2;
	
	@Override
	public void init() {
		TextureManager.registerTexture("images/arial.png", FONT_TEXTURE);
		
		mWorld = new World();
		Road b = new Road(1.75f,12.14f,Road.Direction.NORTH,Road.Direction.EAST);
		b.getClass().toString();
		
		TextEntity num1 = new TextEntity(0,0.05*mWorld.getHeight(),b.getX()+"");
		mWorld.addEntity(num1);
		TextEntity num2 = new TextEntity(0,0.10*mWorld.getHeight(),b.getY()+"");
		mWorld.addEntity(num2);
		TextEntity num3 = new TextEntity(0,0.15*mWorld.getHeight(),b.getDirection(1.75f, 13.14f).x+","+b.getDirection(1.75f, 13.14f).y);
		mWorld.addEntity(num3);
		TextEntity num4 = new TextEntity(0,0.20*mWorld.getHeight(),b.getDirection(3.35f, 12.14f).x+","+b.getDirection(3.35f, 12.14f).y);
		mWorld.addEntity(num4);
	}
}

class TextEntity extends Entity {
	public TextEntity() {
		Texture tex = TextureManager.getTexture(FontGame.FONT_TEXTURE);
		TextSprite ts = new TextSprite(tex);
		ts.setMessage("hello Font");
		mRenderable = ts;
	}
	
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
		// Don't animate me...
	}
	
	@Override 
	public void draw(GL10 gl) {
		mRenderable.draw(gl);
	}
}
