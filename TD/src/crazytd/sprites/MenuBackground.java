package crazytd.sprites;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;

public class MenuBackground extends Entity {

	public boolean isVisible;

	public MenuBackground() {
		super();
	}

	@Override
	public void draw(GL10 gl) {
		if(isVisible){
			super.draw(gl);
		}
	}

}