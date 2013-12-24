package crazytd.map;

import javax.microedition.khronos.opengles.GL10;

import org.robobrain.sdk.game.Entity;
import org.robobrain.sdk.graphics.Sprite;
import org.robobrain.sdk.graphics.Texture;
import org.robobrain.sdk.graphics.TextureManager;
import org.robobrain.sdk.graphics.Vector;


public class Block {
	public enum Direction{
		NORTH, SOUTH, EAST, WEST
	}
	
	private int tileX;		// the column number in the map
	private int tileY;		// the row number in the map
	protected float x;		// the x coordinate in the map
	protected float y;		// the y coordinate in the map
	protected MapElement m;
	//protected BlockType type;//block type
	
	//Temporary variables
	// Somehow..95-100 works better than 64... TODO: look into it
	public static final float tileSize = 64; //the edge length of a tile 
	//protected float BORDER_WIDTH = 20;
	//set up a tile by column number and row number
	public Block(int col, int row){
		this.tileX = col;
		this.tileY = row;
		//this.x = col * tileSize + tileSize/2 + BORDER_WIDTH;
		//this.y = row * tileSize + tileSize/2 + BORDER_WIDTH;
		this.x = tileX * tileSize + tileSize/2;
		this.y = tileY * tileSize + tileSize/2;
	}

	//set up a tile by given a coordinate
	public Block(float x, float y){
		this.tileX = Math.round(x/tileSize);			//round to the nearest int
		this.tileY = Math.round(y/tileSize);			//round to the nearest int
		//this.x = tileX * tileSize + tileSize/2 + BORDER_WIDTH;
		//this.y = tileY * tileSize + tileSize/2 + BORDER_WIDTH;
		this.x = tileX * tileSize + tileSize/2;
		this.y = tileY * tileSize + tileSize/2;

	}
	public Vector getCoordinate(){
		return new Vector((float)x,(float)y);
	}
	
	public float getX(){
		return (float)x;
	}
	
	public float getY(){
		return (float)y;
	}

	public void bindTextureIndex(int textureIndex){
		m = new MapElement(x, y, textureIndex);
	}
	
	public Entity getMapElement(){
		return m;
	}

}
class MapElement extends Entity {
	//before calling this constructor, in IextureManager, please register the correct texture to the textureIndex 
	public MapElement(float x, float y, int textureIndex) {
		super();
		Texture t = TextureManager.getTexture(textureIndex);
		Sprite s = new Sprite(t, 64, 64, 1);
		this.x = x;
		this.y = y;
		mRenderable = s;
	}

}
