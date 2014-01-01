package crazytd.sprites;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.game.World;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;

public class Icon extends Entity{
	/** an Icon in the world
	 * 
	 * @param t:		texture
	 * @param width:	the width of the icon
	 * @param height:	the height of the icon
	 * @param relativeX:the relative x-coordinate of the icon, should be between 0 and 1, 0.5 if outside this range
	 * @param relativeY:the relative y-coordinate of the icon, should be between 0 and 1, 0.5 if outside this range
	 * @param myWorld:	the world the icon is in
	 */
	public Icon(Texture t, float width, float height, float relativeX, float relativeY, World myWorld){
		if(relativeX > 1 || relativeX<0) relativeX = 0.5f;
		if(relativeY > 1 || relativeY<0) relativeY = 0.5f;
		this.x = myWorld.getWidth()*relativeX;
		this.y = myWorld.getHeight()*relativeY;
		this.mRenderable = new Sprite(t , Math.round(width), Math.round(height), 1);
	}
}
